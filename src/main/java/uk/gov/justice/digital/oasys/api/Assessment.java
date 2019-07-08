package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

@Value
@Builder(toBuilder = true)
public class Assessment {
    private Long oasysSetId;
    private String assessmentType;
    private String historicStatus;
    private String assessmentStatus;
    private AssessmentVersion assessmentVersion;
    private LocalDateTime createdDateTime;
    private boolean completed;
    private LocalDateTime completedDateTime;
    private boolean voided;
    private Map<String, Section> sections;
    private List<OasysBcsPart> oasysBcsParts;
    private QaReview qaReview;

    public Optional<Boolean> getTspEligible() {
        if (!isLayer3()) {
            return Optional.empty();
        }

        var section2 = Optional.ofNullable(sections.get("2"));
        var section7 = Optional.ofNullable(sections.get("7"));
        var section11 = Optional.ofNullable(sections.get("11"));
        var section12 = Optional.ofNullable(sections.get("12"));

        var answer2_6 = section2
                .filter(s2 -> s2.getQuestions() != null)
                .flatMap(s2 -> Optional.ofNullable(s2.getQuestions().get("2.6")))
                .flatMap(Question::getAnswer);

        var answer7_2 = section7.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s7 -> Optional.ofNullable(s7.getQuestions().get("7.2")))
                .flatMap(Question::getAnswer);

        var answer11_4 = section11.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.4")))
                .flatMap(Question::getAnswer);

        var answer11_6 = section11.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.6")))
                .flatMap(Question::getAnswer);

        var answer11_7 = section11.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.7")))
                .flatMap(Question::getAnswer);

        var answer11_9 = section11.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.9")))
                .flatMap(Question::getAnswer);

        var answer12_1 = section12.filter(s2 -> s2.getQuestions() != null)
                .flatMap(s12 -> Optional.ofNullable(s12.getQuestions().get("12.1")))
                .flatMap(Question::getAnswer);


        try {

            var sum = ImmutableList.of(answer2_6, answer7_2, answer11_4, answer11_6, answer11_7, answer11_9, answer12_1)
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::orElseThrow)
                    .map(a -> Optional.ofNullable(a.getScore()))
                    .map(Optional::orElseThrow)
                    .reduce(0L, Long::sum);

            boolean pivoted = (sum >= 7L ||
                    sum >= 5 && answer11_6.isPresent() && answer11_6.get().getScore() == 2L ||
                    sum >= 5 && answer11_7.isPresent() && answer11_7.get().getScore() == 2L);

            boolean missingAnswers = ImmutableList.of(answer2_6, answer7_2, answer11_4, answer11_6, answer11_7, answer11_9, answer12_1)
                    .stream().anyMatch(Optional::isEmpty);

            if (pivoted) {
                return Optional.of(true);
            }

            if (!missingAnswers) {
                return Optional.of(false);
            }

            return null;

        } catch (NoSuchElementException nsee) {
            return null;
        }
    }

    public List<AssessmentNeed> getLayer3SentencePlanNeeds() {

        if (!isLayer3()) {
            return new ArrayList<>();
        }

        Map<String, Map<String, String>> planSections = getPlanSections();
        Map<String, String> questionHarmNeeds = getQuestionHarmNeeds();

        List<Section> filteredSections = sections.entrySet().stream()
                .filter(s -> planSections.containsKey(s.getKey()))
                .filter(s -> Objects.nonNull(s.getValue().getRefSectionCode()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        List<AssessmentNeed> assessmentNeeds = new ArrayList<>();

        for (Section section : filteredSections) {
            Map<String, String> planSection = Optional.ofNullable(planSections.get(section.getRefSectionCode()))
                    .orElse(new HashMap<>());

            var riskHarm = sectionHasRiskOfHarm(section, planSection);
            var riskReoffending = sectionHasRiskOfReoffending(section, planSection);
            var overThreshold = sectionIsOverThreshold(section);
            var flagged = Optional.ofNullable(section.getLowScoreAttentionNeeded()).orElse(false);

            if (Stream.of(overThreshold, riskHarm, riskReoffending, flagged).anyMatch(e -> e.equals(true))) {
                assessmentNeeds.add(new AssessmentNeed(section.getRefSection().getShortDescription(),
                        overThreshold, riskHarm, riskReoffending, flagged));
            }

            questionHarmNeeds.entrySet().stream().filter(e -> e.getValue().equals(section.getRefSectionCode())).forEach(e -> {
                Optional<Question> question = Optional.ofNullable(section.getQuestions().get(e.getKey()));
                if (questionHasAnswer(question, "YES")) {
                    assessmentNeeds.add(new AssessmentNeed(question.map(q -> q.getQuestionText()).orElse(""), false, true, false, false));
                }
            });

        }
        return assessmentNeeds;
    }


    private boolean sectionIsOverThreshold(Section section) {
        return Optional.ofNullable(section.getSectionOtherRawScore()).orElse(0L) >= Optional.ofNullable(section.getRefSection()).map(s -> s.getRefCrimNeedScoreThreshold()).orElse(Long.MAX_VALUE);
    }

    private boolean sectionHasRiskOfReoffending(Section section, Map<String, String> planSection) {
        return questionHasAnswer(Optional.ofNullable(section.getQuestions().get(Optional.ofNullable(planSection.get("reoffendingQuestion")).orElse(""))), "YES");
    }

    private boolean sectionHasRiskOfHarm(Section section, Map<String, String> planSection) {
        return questionHasAnswer(Optional.ofNullable(section.getQuestions().get(Optional.ofNullable(planSection.get("harmQuestion")).orElse(""))), "YES");
    }

    private boolean questionHasAnswer(Optional<Question> question, String expectedAnswer) {
        return question.map(e -> e.getAnswer().get()).map(Answer::getRefAnswerCode).orElse("NO").equals("YES");
    }

    private Map<String, Map<String, String>> getPlanSections() {
        return Map.ofEntries(
                entry("10"
                        , Map.of(
                                "harmQuestion", "10.98",
                                "reoffendingQuestion", "10.99")),
                entry("11"
                        , Map.of(
                                "harmQuestion", "11.98",
                                "reoffendingQuestion", "11.99")),
                entry("12"
                        , Map.of(
                                "harmQuestion", "12.98",
                                "reoffendingQuestion", "12.99")),
                entry("3"
                        , Map.of(
                                "harmQuestion", "3.98",
                                "reoffendingQuestion", "3.99")),
                entry("4"
                        , Map.of(
                                "harmQuestion", "4.96",
                                "reoffendingQuestion", "4.98")),
                entry("5"
                        , Map.of(
                                "harmQuestion", "5.98",
                                "reoffendingQuestion", "5.99")),
                entry("6"
                        , Map.of(
                                "harmQuestion", "6.98",
                                "reoffendingQuestion", "6.99")),
                entry("7"
                        , Map.of(
                                "harmQuestion", "7.98",
                                "reoffendingQuestion", "7.99")),
                entry("8"
                        , Map.of(
                                "harmQuestion", "8.97",
                                "reoffendingQuestion", "8.98")),
                entry("9"
                        , Map.of(
                                "harmQuestion", "9.98",
                                "reoffendingQuestion", "9.99")),
                entry("ROSHSUM",
                        new HashMap<>()),
                entry("FA31",
                        new HashMap<>()),
                entry("ROSHFULL",
                        new HashMap<>())
        );
    }

    private Map<String, String> getQuestionHarmNeeds() {
        return Map.of("sum6.1", "ROSHSUM", "sum6.2", "ROSHSUM",
                "sum6.4", "ROSHSUM", "sum6.5", "ROSHSUM", "FA31", "ROSHFULL", "7.4",
                "7", "6.7", "6", "FA51", "ROSHFULL");
    }


    @JsonIgnore
    public boolean isLayer3() {
        return "LAYER_3".equals(assessmentType);
    }

}
