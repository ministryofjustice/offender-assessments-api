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

import java.util.Map;
import java.util.Optional;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;

public class AssessmentTest {

    @Test
    public void tspEligibilityIsIndicatedOnCompletedLayer3() throws JsonProcessingException {
        Assessment assessment = Assessment.builder().
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
        Assessment assessment = Assessment.builder().
                sections(completeLayer3Sections())
                .assessmentType("LAYER_666")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        Assertions.assertThat(json.contains("tspEligible")).isFalse();
    }

    @Test
    public void tspEligibilityIsAbsentWhenLayer3ButIncomplete() throws JsonProcessingException {
        Assessment assessment = Assessment.builder().
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


        Assessment assessment = Assessment.builder()
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


        Assessment assessment = Assessment.builder()
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


        Assessment assessment = Assessment.builder()
                .sections(sectionsWithScoresOf(completeLayer3Sections(), builder.build()))
                .assessmentType("LAYER_3")
                .build();

        ObjectMapper objectMapper = new OffenderAssessmentsApi().objectMapper();

        final String json = objectMapper.writeValueAsString(assessment);

        DocumentContext ctx = JsonPath.parse(json);

        assertThat(ctx).jsonPathAsBoolean("$.tspEligible").isTrue();
    }

    private Map<String, Section> sectionsWithScoresOf(Map<String, Section> sections, Map<String, Long> scores) {
        return Maps.transformValues(sections, input -> Section.builder()
                .questions(questionsWithScoresOf(input.getQuestions(), scores))
                .build());
    }

    private Map<String, Question> questionsWithScoresOf(Map<String, Question> questions, Map<String, Long> scores) {
        return Maps.transformEntries(questions, (key, value) -> Question.builder().answer(Optional.of(Answer.builder().ovpScore(scores.get(key)).build())).build());
    }

    private Map<String, Section> incompleteLayer3Sections() {

        Section section2 = Section.builder()
                .refSectionCode("2")
                .questions(ImmutableMap.of("2.6", Question.builder().build()))
                .build();

        Section section7 = Section.builder()
                .refSectionCode("7")
                .questions(ImmutableMap.of("7.2", Question.builder().
                        answer(Optional.of(Answer.builder().ogpScore(0L).build())).build()))
                .build();

        Section section11 = Section.builder()
                .refSectionCode("11")
                .questions(ImmutableMap.of("11.4", Question.builder().
                                answer(Optional.of(Answer.builder().ogpScore(0L).build())).build(),
                        "11.6", Question.builder().
                                answer(Optional.of(Answer.builder().ogpScore(0L).build())).build(),
                        "11.7", Question.builder().
                                answer(Optional.of(Answer.builder().ogpScore(0L).build())).build(),
                        "11.9", Question.builder().
                                answer(Optional.of(Answer.builder().ogpScore(0L).build())).build()))
                .build();

        Section section12 = Section.builder()
                .refSectionCode("12")
                .questions(ImmutableMap.of("12.1", Question.builder().
                        answer(Optional.of(Answer.builder().ogpScore(0L).build())).build()))
                .build();

        return ImmutableMap.of("2", section2, "7", section7, "11", section11, "12", section12);
    }

    private Map<String, Section> completeLayer3Sections() {
        Section section2 = Section.builder()
                .refSectionCode("2")
                .questions(ImmutableMap.of("2.6", Question.builder().
                        answer(Optional.of(Answer.builder().ovpScore(0L).build())).build()))
                .build();

        return ImmutableMap.<String, Section>builder().putAll(Maps.filterEntries(incompleteLayer3Sections(), s -> !s.getKey().equals("2"))).put("2", section2).build();

    }
}