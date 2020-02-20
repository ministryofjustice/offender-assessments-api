package uk.gov.justice.digital.oasys.api;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAnswer;

import java.util.List;
import java.util.Map;

import static java.util.Collections.EMPTY_MAP;
import static org.assertj.core.api.Assertions.assertThat;

public class Assessment_SentencePlanNeedsTest {


    @Test
    public void shouldIncludeInSentencePlanNeedsWhenAllCriteriaMet() {

        //Matches all criteria
        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", answerYes(),
                        "10.99", answerYes()))
                .build();

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);

        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getName).containsExactly("Emotional Wellbeing");

        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getOverThreshold).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getRiskOfHarm).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getRiskOfReoffending).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getFlaggedAsNeed).containsExactly(true);

    }

    @Test
    public void shouldReturnEmptySetWhenAssessmentisNotLayer3() {

        //Matches all criteria
        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", answerYes(),
                        "10.99", answerYes()))
                .build();


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_1")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(0);
    }

    @Test
    public void shouldNotFailOnMissingQuestions() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(EMPTY_MAP)
                .build();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotFailOnUnSetLowScoreFlagged() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", answerYes(),
                        "10.99", answerYes()))
                .build();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotFailOnUnSetSectionRawScore() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .refSectionCode("10")
                .lowScoreAttentionNeeded(true)
                .questions(ImmutableMap.of("10.98", answerYes(), "10.99", answerYes()))
                .build();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotIncludeInSentencePlanNeedsWhenNoCriteriaMet() {

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).isEmpty();

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenOverThreshold() {

        //Section has score of 6 which is greater than ref score of 5
        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", answerNo(),"10.99", answerNo()))
                .build();

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(AssessmentNeed::getOverThreshold).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getName).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedLowScore() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", answerNo(),"10.99", answerNo()))
                .build();

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(AssessmentNeed::getOverThreshold).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getName).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedAsRiskHarm() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("10.98", answerYes(),"10.99", answerYes()))
                .build();

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(AssessmentNeed::getRiskOfHarm).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getName).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedAsRiskReoffending() {

        SectionDto section10 = SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("10.98", answerNo(),"10.99", answerYes()))
                .build();

        SectionDto section11 = getNotMatchSection();

        AssessmentDto assessment = AssessmentDto.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(AssessmentNeed::getRiskOfReoffending).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(AssessmentNeed::getName).containsExactly("Emotional Wellbeing");

    }

    private SectionDto getNotMatchSection() {

        return SectionDto.builder()
                .refSection(RefSectionDto.builder().refCrimNeedScoreThreshold(5L).shortDescription("Thinking Skills").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("11")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("11.98", answerNo(),"11.99", answerNo()))
                .build();
    }

    private static QuestionDto answerNo(){
        OasysAnswer oasysAnswer = new OasysAnswer();
        RefAnswer refAnswer = new RefAnswer();
        refAnswer.setRefAnswerCode("NO");
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

    private static QuestionDto answerYes(){
        OasysAnswer oasysAnswer = new OasysAnswer();
        RefAnswer refAnswer = new RefAnswer();
        refAnswer.setRefAnswerCode("YES");
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

}