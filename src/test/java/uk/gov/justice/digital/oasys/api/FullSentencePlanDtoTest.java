package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class FullSentencePlanDtoTest {

    @Test
    public void shouldReturnSentencePlanDtoFromOASysSetEntity() {

        OasysSet oasysSet = ControllerServiceTestContext.layer3AssessmentOasysSetWithFullSentencePlan(123L);

        var sentencePlan = FullSentencePlanDto.from(oasysSet);

        assertThat(sentencePlan.getOasysSetId()).isEqualTo(123l);
        assertThat(sentencePlan.getObjectives()).hasSize(2);
        assertThat(sentencePlan.getQuestions()).hasSize(3);
    }

    @Test
    public void shouldUseEarliestObjectiveDateForStartDate() {

        OasysSet oasysSet = ControllerServiceTestContext.layer3AssessmentOasysSetWithFullSentencePlan(123L);

        var sentencePlan = FullSentencePlanDto.from(oasysSet);
        assertThat(sentencePlan.getCreatedDate()).isEqualToIgnoringSeconds(LocalDateTime.of(2019, 11,28, 9, 00));
    }

    @Test
    public void shouldReturnOASysSetCompletedDateAsSentencePlanCompletedDate() {

        OasysSet oasysSet = OasysSet.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .assessmentStatus(RefElement.builder().build())
                .oasysSections(Collections.emptySet())
                .sspObjectivesInSets(Set.of(
                        SspObjectivesInSet.builder()
                                .createDate(LocalDateTime.now().minusDays(10))
                                .build()))
                .dateCompleted(LocalDateTime.now().minusDays(1))
                .oasysSetPk(123l).build();

        var sentencePlan = FullSentencePlanDto.from(oasysSet);

        assertThat(sentencePlan.getOasysSetId()).isEqualTo(123l);
        assertThat(sentencePlan.getCreatedDate()).isEqualToIgnoringMinutes(LocalDateTime.now().minusDays(10));
        assertThat(sentencePlan.getCompletedDate()).isEqualToIgnoringMinutes(LocalDateTime.now().minusDays(1));

    }

    @Test
    public void sentencePlanHasNullCompletedDateIfNotPresent() {
        var today = LocalDateTime.now();


        var oasysSet = OasysSet.builder()
                .createDate(today)
                .dateCompleted(null)
                .oasysSections(Collections.emptySet())
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(today).build()).build()))
                .build();

        final FullSentencePlanDto actual = FullSentencePlanDto.from(oasysSet);

        assertThat(actual.getCompletedDate()).isNull();
    }

    @Test
    public void shouldReturnRefQuestionsForSPSectionsInAdditionToOASysQuestions() {


        var section = ControllerServiceTestContext.getSentencePlanSection();
        section.getRefSection().setRefQuestions(List.of(RefQuestion.builder()
                .refQuestionUk(1l)
                .refQuestionCode("IP.40")
                .displaySort(1l)
                .refSectionQuestion("Ref Question Text").build()));

        OasysSet oasysSet = OasysSet.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .assessmentStatus(RefElement.builder().build())
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(LocalDateTime.now()).build()).build()))
                .dateCompleted(LocalDateTime.now().minusDays(1))
                .oasysSections(Set.of(section))
                .oasysSetPk(123l).build();

        var sentencePlan = FullSentencePlanDto.from(oasysSet);
        assertThat(sentencePlan.getQuestions()).hasSize(3);
        assertThat(sentencePlan.getQuestions()).containsOnlyKeys("IP.40", "IP.1", "IP.2");
    }

}