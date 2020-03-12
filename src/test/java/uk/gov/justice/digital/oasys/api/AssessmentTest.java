package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import uk.gov.justice.digital.oasys.OffenderAssessmentsApi;
import uk.gov.justice.digital.oasys.jpa.entity.*;

import java.util.Map;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;

public class AssessmentTest {

/*    @Test
    public void childSafeguardingIsIndicatedWhenR2_1_YES() throws JsonProcessingException {

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        var assessment = AssessmentDto.builder()
                .sections(Map.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(Map.of(
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
                .sections(Map.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(Map.of(
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
                .sections(Map.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(Map.of(
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
                .sections(Map.of(
                        "ROSH",
                        SectionDto.builder()
                                .refSectionCode("ROSH")
                                .questions(Map.of(
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
    }*/

}