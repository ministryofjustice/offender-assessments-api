package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import uk.gov.justice.digital.oasys.OffenderAssessmentsApi;
import uk.gov.justice.digital.oasys.jpa.entity.*;

import java.util.Map;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;

public class AssessmentTest {

    @Test
    public void tspEligibilityIsIndicatedOnCompletedLayer3() throws JsonProcessingException {
        AssessmentDto assessment = AssessmentDto.builder().
                sections(completeLayer3Sections())
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isFalse();
    }

    @Test
    public void tspEligibilityIsAbsentOnNotLayer3() throws JsonProcessingException {
        AssessmentDto assessment = AssessmentDto.builder().
                sections(completeLayer3Sections())
                .assessmentType("LAYER_666")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        Assertions.assertThat(json.contains("tspEligible")).isFalse();
    }

    @Test
    public void tspEligibilityIsAbsentWhenLayer3ButIncomplete() throws JsonProcessingException {
        AssessmentDto assessment = AssessmentDto.builder().
                sections(incompleteLayer3Sections())
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        Assertions.assertThat(json.contains("tspEligible")).isFalse();
    }

    @Test
    public void tspEligibilityWhenTotalScore7OrMore() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 1L).put("11.4", 1L).put("11.6", 1L).put("11.7", 1L).put("11.9", 1L).put("12.1", 1L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();
        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isTrue();
    }

    /*
    b) Is total score 5+ AND 11.6 scores 2Yes   No c) Is total score 5+ AND 11.7 scores 2
     */

    @Test
    public void tspEligibilityWhenTotalScore5OrMoreAnd11_6Scores2() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 1L).put("11.4", 1L).put("11.6", 2L).put("11.7", 0L).put("11.9", 0L).put("12.1", 0L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();
        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isTrue();
    }

    @Test
    public void tspEligibilityWhenTotalScore5OrMoreAnd11_7Scores2() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 1L).put("11.4", 1L).put("11.6", 0L).put("11.7", 2L).put("11.9", 0L).put("12.1", 0L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isTrue();
    }

    @Test
    public void tspIneligibilityWhenTotalScore5But11_7NotScores2() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 1L).put("11.4", 1L).put("11.6", 1L).put("11.7", 1L).put("11.9", 0L).put("12.1", 0L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();
        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isFalse();
    }

    @Test
    public void tspIneligibilityWhenTotalScore5But11_6NotScores2() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 1L).put("11.4", 1L).put("11.6", 1L).put("11.7", 1L).put("11.9", 0L).put("12.1", 0L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();
        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isFalse();
    }

    @Test
    public void tspIneligibilityWhenTotalScoreLessThan5() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 1L).put("7.2", 0L).put("11.4", 0L).put("11.6", 0L).put("11.7", 0L).put("11.9", 0L).put("12.1", 0L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isFalse();
    }

    private Map<String, SectionDto> sectionsWithScoresOf(Map<String, SectionDto> sections, Map<String, Long> scores) {
        return Maps.transformValues(sections, input -> SectionDto.builder()
                .questions(questionsWithScoresOf(input.getQuestions(), scores))
                .build());
    }

    private Map<String, QuestionDto> questionsWithScoresOf(Map<String, QuestionDto> questions, Map<String, Long> scores) {
        return Maps.transformEntries(questions, (key, value) -> answerOGP(scores.get(key)));
    }

    private Map<String, SectionDto> incompleteLayer3Sections() {

        SectionDto section2 = SectionDto.builder()
                .refSectionCode("2")
                .questions(ImmutableMap.of("2.6", new QuestionDto()))
                .build();

        SectionDto section7 = SectionDto.builder()
                .refSectionCode("7")
                .questions(ImmutableMap.of("7.2", answerOGPZero()))
                .build();

        SectionDto section11 = SectionDto.builder()
                .refSectionCode("11")
                .questions(ImmutableMap.of("11.4", answerOGPZero(),
                        "11.6", answerOGPZero(),
                        "11.7", answerOGPZero(),
                        "11.9", answerOGPZero()))
                .build();

        SectionDto section12 = SectionDto.builder()
                .refSectionCode("12")
                .questions(ImmutableMap.of("12.1", answerOGPZero()))
                .build();

        return ImmutableMap.of("2", section2, "7", section7, "11", section11, "12", section12);
    }

    private Map<String, SectionDto> completeLayer3Sections() {
        SectionDto section2 = SectionDto.builder()
                .refSectionCode("2")
                .questions(ImmutableMap.of("2.6", answerOVPZero()))
                .build();

        return ImmutableMap.<String, SectionDto>builder().putAll(Maps.filterEntries(incompleteLayer3Sections(), s -> !s.getKey().equals("2"))).put("2", section2).build();

    }

    @Test
    public void tspEligibilityWhenTotalScore7OrMoreOnIncompleteAssessment() throws JsonProcessingException {
        var builder = ImmutableMap.<String, Long>builder();
        builder.put("2.6", 7L);


        AssessmentDto assessment = AssessmentDto.builder()
                .sections(sectionsWithScoresOf(ImmutableMap.of(
                        "2",
                        SectionDto.builder()
                                .refSectionCode("2")
                                .questions(ImmutableMap.of("2.6", answerOVPZero()))
                                .build()), builder.build()))
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isTrue();
    }

    @Test
    public void childSafeguardingIsIndicatedWhenR2_1_YES() throws JsonProcessingException {

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .sections(ImmutableMap.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(ImmutableMap.of(
                                        "R2.1", answerYes(),
                                        "R2.2", answerNo()))
                                .build()))
                .assessmentType("LAYER_3")
                .build();


        final String json = objectMapper.writeValueAsString(assessment);
        DocumentContext ctx = JsonPath.parse(json);
        assertThat(ctx).jsonPathAsBoolean("$.childSafeguardingIndicated").isTrue();
    }

    @Test
    public void childSafeguardingIsIndicatedWhenR2_2_YES() throws JsonProcessingException {

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .sections(ImmutableMap.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(ImmutableMap.of(
                                        "R2.1", answerNo(),
                                        "R2.2", answerYes()))
                                .build()))
                .assessmentType("LAYER_3")
                .build();


        final String json = objectMapper.writeValueAsString(assessment);
        DocumentContext ctx = JsonPath.parse(json);
        assertThat(ctx).jsonPathAsBoolean("$.childSafeguardingIndicated").isTrue();
    }

    @Test
    public void childSafeguardingIsIndicatedWhenR2_1_YESAndR2_2_YES() throws JsonProcessingException {

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .sections(ImmutableMap.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(ImmutableMap.of(
                                        "R2.1", answerYes(),
                                        "R2.2", answerYes()))
                                .build()))
                .assessmentType("LAYER_3")
                .build();


        final String json = objectMapper.writeValueAsString(assessment);
        DocumentContext ctx = JsonPath.parse(json);
        assertThat(ctx).jsonPathAsBoolean("$.childSafeguardingIndicated").isTrue();
    }

    @Test
    public void childSafeguardingIsFalseWhenNotIndicated() throws JsonProcessingException {

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .sections(ImmutableMap.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(ImmutableMap.of(
                                        "R2.1", answerNo(),
                                        "R2.2", answerNo()))
                                .build()))
                .assessmentType("LAYER_3")
                .build();


        final String json = objectMapper.writeValueAsString(assessment);
        DocumentContext ctx = JsonPath.parse(json);
        assertThat(ctx).jsonPathAsBoolean("$.childSafeguardingIndicated").isFalse();
    }


    @Test
    public void childIndicatorsAreAbsentWhenInsufficientDataInAssessment() throws JsonProcessingException {
        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .assessmentType("LAYER_3")
                .build();


        final String json = objectMapper.writeValueAsString(assessment);
        Assertions.assertThat(json.contains("childSafeguardingIndicated")).isFalse();
    }

    private static QuestionDto answerNo(){
        OasysAnswer oasysAnswer = new OasysAnswer();
        uk.gov.justice.digital.oasys.jpa.entity.RefAnswer refAnswer = new uk.gov.justice.digital.oasys.jpa.entity.RefAnswer();
        refAnswer.setRefAnswerCode("NO");
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

    private static QuestionDto answerYes(){
        OasysAnswer oasysAnswer = new OasysAnswer();
        uk.gov.justice.digital.oasys.jpa.entity.RefAnswer refAnswer = new RefAnswer();
        refAnswer.setRefAnswerCode("YES");
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

    private static QuestionDto answerOVPZero(){
        OasysAnswer oasysAnswer = new OasysAnswer();
        uk.gov.justice.digital.oasys.jpa.entity.RefAnswer refAnswer = new uk.gov.justice.digital.oasys.jpa.entity.RefAnswer();
        refAnswer.setOvpScore(0L);
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

    private static QuestionDto answerOGPZero(){
        return answerOGP(0);
    }

    private static QuestionDto answerOGP(long score){
        OasysAnswer oasysAnswer = new OasysAnswer();
        uk.gov.justice.digital.oasys.jpa.entity.RefAnswer refAnswer = new uk.gov.justice.digital.oasys.jpa.entity.RefAnswer();
        refAnswer.setOgpScore(score);
        oasysAnswer.setRefAnswer(refAnswer);
        OasysQuestion oasysQuestion = new OasysQuestion();
        oasysQuestion.setOasysAnswer(oasysAnswer);
        return QuestionDto.from(oasysQuestion);
    }

}