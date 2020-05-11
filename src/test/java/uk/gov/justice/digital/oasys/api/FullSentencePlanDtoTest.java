package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class FullSentencePlanDtoTest {

    @Test
    public void shouldReturnSentencePlanDtoFromOASysAssessment() {
        var assessment = ApiTestContext.layer3AssessmentWithFullSentencePlan(123L);
        var section = Optional.ofNullable(ApiTestContext.getSentencePlanSection());
        var sentencePlan = FullSentencePlanDto.from(assessment, section);
        assertThat(sentencePlan.getOasysSetId()).isEqualTo(123l);
        assertThat(sentencePlan.getObjectives()).hasSize(2);
        assertThat(sentencePlan.getQuestions()).hasSize(3);
    }

    @Test
    public void shouldReturnNullWhenNoISPorRSPSectionFoundAndNoObjectives() {
        var assessment = Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .assessmentStatus("COMPLETED")
                .oasysSections(Collections.emptySet())
                .sspObjectivesInSets(Collections.emptySet())
                .dateCompleted(LocalDateTime.now().minusDays(1))
                .oasysSetPk(123l).build();
        var sentencePlan = FullSentencePlanDto.from(assessment, Optional.empty());
        assertThat(sentencePlan).isNull();
    }

    @Test
    public void shouldUseCreatedDateForStartDate() {
        var assessment = ApiTestContext.layer3AssessmentWithFullSentencePlan(123L);
        var section = Optional.ofNullable(ApiTestContext.getSentencePlanSection());
        var sentencePlan = FullSentencePlanDto.from(assessment, section);
        assertThat(sentencePlan.getCreatedDate()).isEqualToIgnoringSeconds(LocalDateTime.now().minusDays(1));
    }

    @Test
    public void shouldReturnOASysSetCompletedDateAsSentencePlanCompletedDate() {
        var assessment = Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .assessmentStatus("COMPLETED")
                .oasysSections(Collections.emptySet())
                .sspObjectivesInSets(Set.of(
                        SspObjectivesInSet.builder()
                                .createDate(LocalDateTime.now().minusDays(10))
                                .build()))
                .dateCompleted(LocalDateTime.now().minusDays(1))
                .oasysSetPk(123l).build();
        var section = Optional.ofNullable(ApiTestContext.getSentencePlanSection());
        var sentencePlan = FullSentencePlanDto.from(assessment,section);

        assertThat(sentencePlan.getOasysSetId()).isEqualTo(123l);
        assertThat(sentencePlan.getCompletedDate()).isEqualToIgnoringMinutes(LocalDateTime.now().minusDays(1));

    }

    @Test
    public void sentencePlanHasNullCompletedDateIfNotPresent() {
        var today = LocalDateTime.now();
        var assessment = Assessment.builder()
                .createDate(today)
                .dateCompleted(null)
                .oasysSections(Collections.emptySet())
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(today).build()).build()))
                .build();
        var section = Optional.ofNullable(ApiTestContext.getSentencePlanSection());
        var actual = FullSentencePlanDto.from(assessment, section);

        assertThat(actual.getCompletedDate()).isNull();
    }


    @Test
    public void shouldReturnRefQuestionsForSPSectionsInAdditionToOASysQuestions() {
        var section = ApiTestContext.getSentencePlanSection();
        section.getRefSection().setRefQuestions(List.of(RefQuestion.builder()
                .refQuestionUk(1l)
                .refQuestionCode("IP.40")
                .displaySort(1l)
                .refSectionQuestion("Ref Question Text").build()));

        Assessment assessment = Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .assessmentStatus("COMPLETED")
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(LocalDateTime.now()).build()).build()))
                .dateCompleted(LocalDateTime.now().minusDays(1))
                .oasysSections(Set.of(section))
                .oasysSetPk(123l).build();

        var sentencePlan = FullSentencePlanDto.from(assessment, Optional.ofNullable(section));
        assertThat(sentencePlan.getQuestions()).hasSize(3);
        assertThat(sentencePlan.getQuestions()).containsOnlyKeys("IP.40", "IP.1", "IP.2");
    }
}