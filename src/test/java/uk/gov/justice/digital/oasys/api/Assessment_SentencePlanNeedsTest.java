package uk.gov.justice.digital.oasys.api;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.EMPTY_MAP;
import static org.assertj.core.api.Assertions.assertThat;

public class Assessment_SentencePlanNeedsTest {


    @Test
    public void shouldIncludeInSentencePlanNeedsWhenAllCriteriaMet() {

        //Matches all criteria
        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);

        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("Emotional Wellbeing");

        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.isOverThreshold()).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.isRiskOfHarm()).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.isRiskOfReoffending()).containsExactly(true);

        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.isFlaggedAsNeed()).containsExactly(true);

    }

    @Test
    public void shouldReturnEmptySetWhenAssessmentisNotLayer3() {

        //Matches all criteria
        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();


        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_1")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(0);
    }

    @Test
    public void shouldNotFailOnMissingQuestions() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(true)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(EMPTY_MAP)
                .build();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotFailOnUnSetLowScoreFlagged() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotFailOnUnSetSectionRawScore() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .refSectionCode("10")
                .lowScoreAttentionNeeded(true)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1);
    }

    @Test
    public void shouldNotIncludeInSentencePlanNeedsWhenNoCriteriaMet() {

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).isEmpty();

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenOverThreshold() {

        //Section has score of 6 which is greater than ref score of 5
        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(i->i.isOverThreshold()).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedLowScore() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(6L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(i->i.isOverThreshold()).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedAsRiskHarm() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(i->i.isRiskOfHarm()).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("Emotional Wellbeing");

    }

    @Test
    public void shouldIncludeInSentencePlanNeedsWhenFlaggedAsRiskReoffending() {

        Section section10 = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Emotional Wellbeing").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("10")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("10.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build(),
                        "10.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("10", section10, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(i->i.isRiskOfReoffending()).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("Emotional Wellbeing");

    }


    @Test
    public void shouldIncludeSpecificQuestionsInSentencePlanNeedsWhenHarmQuestionIsYES() {

        Section sectionR = Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Risk Assessment").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("ROSHSUM")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("sum6.1", Question.builder().questionText("question text").answer(Optional.of(Answer.builder().refAnswerCode("YES").build())).build()))
                .build();

        Section section11 = getNotMatchSection();

        Assessment assessment = Assessment.builder()
                .sections(Map.of("ROSHSUM", sectionR, "11", section11))
                .assessmentType("LAYER_3")
                .build();

        List<AssessmentNeed> layer3SentencePlanNeeds = assessment.getLayer3SentencePlanNeeds();
        assertThat(layer3SentencePlanNeeds).hasSize(1)
                .extracting(i->i.isRiskOfHarm()).containsExactly(true);
        assertThat(layer3SentencePlanNeeds)
                .extracting(i->i.getName()).containsExactly("question text");

    }

    private Section getNotMatchSection() {
        return Section.builder()
                .refSection(RefSection.builder().refCrimNeedScoreThreshold(5L).shortDescription("Thinking Skills").build())
                .lowScoreAttentionNeeded(false)
                .refSectionCode("11")
                .sectionOtherRawScore(1L)
                .questions(ImmutableMap.of("11.98", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build(),
                        "11.99", Question.builder().answer(Optional.of(Answer.builder().refAnswerCode("NO").build())).build()))
                .build();
    }

}