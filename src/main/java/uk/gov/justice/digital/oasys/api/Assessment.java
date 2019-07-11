package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        var section2 = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("2")));
        var section7 = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("7")));
        var section11 = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("11")));
        var section12 = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("12")));

        var answer2_6 = section2
                .filter(s -> s.getQuestions() != null)
                .flatMap(s2 -> Optional.ofNullable(s2.getQuestions().get("2.6")))
                .flatMap(Question::getAnswer);

        var answer7_2 = section7.filter(s -> s.getQuestions() != null)
                .flatMap(s7 -> Optional.ofNullable(s7.getQuestions().get("7.2")))
                .flatMap(Question::getAnswer);

        var answer11_4 = section11.filter(s -> s.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.4")))
                .flatMap(Question::getAnswer);

        var answer11_6 = section11.filter(s -> s.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.6")))
                .flatMap(Question::getAnswer);

        var answer11_7 = section11.filter(s -> s.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.7")))
                .flatMap(Question::getAnswer);

        var answer11_9 = section11.filter(s -> s.getQuestions() != null)
                .flatMap(s11 -> Optional.ofNullable(s11.getQuestions().get("11.9")))
                .flatMap(Question::getAnswer);

        var answer12_1 = section12.filter(s -> s.getQuestions() != null)
                .flatMap(s12 -> Optional.ofNullable(s12.getQuestions().get("12.1")))
                .flatMap(Question::getAnswer);

        var sum = ImmutableList.of(answer2_6, answer7_2, answer11_4, answer11_6, answer11_7, answer11_9, answer12_1)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(a -> Optional.ofNullable(a.getScore()))
                .filter(Optional::isPresent)
                .map(Optional::get)
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

        return Optional.empty();
    }

    public List<AssessmentNeed> getLayer3SentencePlanNeeds() {

        if (!isLayer3()) {
            return new ArrayList<>();
        }

        Map<String, Map<String, String>> planSections = getPlanSections();

        List<Section> filteredSections = Optional.ofNullable(sections)
                .map(s -> s.entrySet().stream()
                        .filter(entry -> planSections.containsKey(entry.getKey()))
                        .filter(entry -> Objects.nonNull(entry.getValue().getRefSectionCode()))
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        List<AssessmentNeed> assessmentNeeds = new ArrayList<>();

        for (Section section : filteredSections) {
            Map<String, String> planSection = Optional.ofNullable(planSections.get(section.getRefSectionCode()))
                    .orElse(new HashMap<>());

            var riskHarm = sectionHasRiskOfHarm(section, planSection);
            var riskReoffending = sectionHasRiskOfReoffending(section, planSection);
            var overThreshold = sectionIsOverThreshold(section);
            var flagged = Optional.ofNullable(section.getLowScoreAttentionNeeded());

            if (Stream.of(overThreshold, riskHarm, riskReoffending, flagged).filter(Optional::isPresent).map(Optional::get).anyMatch(e -> e.equals(true))) {
                assessmentNeeds.add(new AssessmentNeed(section.getRefSection().getShortDescription(),
                        overThreshold.orElse(null), riskHarm.orElse(null), riskReoffending.orElse(null), flagged.orElse(null)));
            }
        }
        return assessmentNeeds;
    }


    private Optional<Boolean> sectionIsOverThreshold(Section section) {
        return Optional.ofNullable(section.getSectionOtherRawScore()).map(score -> score >= Optional.ofNullable(section.getRefSection()).map(RefSection::getRefCrimNeedScoreThreshold).orElse(Long.MAX_VALUE));
    }

    private Optional<Boolean> sectionHasRiskOfReoffending(Section section, Map<String, String> planSection) {
        return questionHasAnswer(Optional.ofNullable(section.getQuestions().get(Optional.ofNullable(planSection.get("reoffendingQuestion")).orElse(""))));
    }

    private Optional<Boolean> sectionHasRiskOfHarm(Section section, Map<String, String> planSection) {
        return questionHasAnswer(Optional.ofNullable(section.getQuestions().get(Optional.ofNullable(planSection.get("harmQuestion")).orElse(""))));
    }

    private Optional<Boolean> questionHasAnswer(Optional<Question> question) {
        return question
                .flatMap(Question::getAnswer)
                .flatMap(a -> Optional.ofNullable(a.getRefAnswerCode()))
                .map("YES"::equals);
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
                                "reoffendingQuestion", "9.99"))
        );

    }

    public Optional<Boolean> getChildSafeguardingIndicated() {
        if (!isLayer3()) {
            return Optional.empty();
        }

        var sectionROSH = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("ROSH")));
        var answerR2_1 = sectionROSH
                .filter(s -> s.getQuestions() != null)
                .flatMap(s -> Optional.ofNullable(s.getQuestions().get("R2.1")))
                .flatMap(Question::getAnswer);

        var sectionROSHFULL = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("ROSHFULL")));

        var answerR7_1a = sectionROSHFULL
                .filter(s -> s.getQuestions() != null)
                .flatMap(s -> Optional.ofNullable(s.getQuestions().get("FA15")))
                .flatMap(Question::getAnswer);

        var answerR7_1b = sectionROSHFULL
                .filter(s -> s.getQuestions() != null)
                .flatMap(s -> Optional.ofNullable(s.getQuestions().get("FA16")))
                .flatMap(Question::getAnswer);

        var answers = Stream.of(answerR2_1, answerR7_1a, answerR7_1b)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Answer::getRefAnswerCode)
                .collect(Collectors.toList());

        if (answers.isEmpty()) {
            return Optional.empty();
        }

        if (answers.contains("YES")) {
            return Optional.of(true);
        }

        return Optional.of(false);
    }

    public Optional<Boolean> getComplyWithChildProtectionPlanIndicated() {
        if (!isLayer3()) {
            return Optional.empty();
        }

        var sectionROSH = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("ROSH")));
        var answerR2_1 = sectionROSH
                .filter(s -> s.getQuestions() != null)
                .flatMap(s -> Optional.ofNullable(s.getQuestions().get("R2.1")))
                .flatMap(Question::getAnswer);

        var sectionROSHFULL = Optional.ofNullable(sections).flatMap(s -> Optional.ofNullable(s.get("ROSHFULL")));

        var answerR7_1b = sectionROSHFULL
                .filter(s -> s.getQuestions() != null)
                .flatMap(s -> Optional.ofNullable(s.getQuestions().get("FA16")))
                .flatMap(Question::getAnswer);

        var answers = Stream.of(answerR2_1, answerR7_1b)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Answer::getRefAnswerCode)
                .collect(Collectors.toList());

        if (answers.isEmpty()) {
            return Optional.empty();
        }

        if (answers.contains("YES")) {
            return Optional.of(true);
        }

        return Optional.of(false);
    }


    @JsonIgnore
    public boolean isLayer3() {
        return "LAYER_3".equals(assessmentType);
    }

}
