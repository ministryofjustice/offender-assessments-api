package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Boolean getTspEligible() {
        if (!isLayer3()) {
            return null;
        }

        Section section2 = sections.get("2");
        Section section7 = sections.get("7");
        Section section11 = sections.get("11");
        Section section12 = sections.get("12");

        var answer2_6 = Optional.ofNullable(section2.getQuestions().get("2.6")).flatMap(Question::getAnswer);
        var answer7_2 = Optional.ofNullable(section7.getQuestions().get("7.2")).flatMap(Question::getAnswer);
        var answer11_4 = Optional.ofNullable(section11.getQuestions().get("11.4")).flatMap(Question::getAnswer);
        var answer11_6 = Optional.ofNullable(section11.getQuestions().get("11.6")).flatMap(Question::getAnswer);
        var answer11_7 = Optional.ofNullable(section11.getQuestions().get("11.7")).flatMap(Question::getAnswer);
        var answer11_9 = Optional.ofNullable(section11.getQuestions().get("11.9")).flatMap(Question::getAnswer);
        var answer12_1 = Optional.ofNullable(section12.getQuestions().get("12.1")).flatMap(Question::getAnswer);

        try {

            var sum = ImmutableList.of(answer2_6, answer7_2, answer11_4, answer11_6, answer11_7, answer11_9, answer12_1)
                    .stream()
                    .map(Optional::orElseThrow)
                    .map(a -> Optional.ofNullable(a.getScore()))
                    .map(Optional::orElseThrow)
                    .reduce(0L, Long::sum);

            return (sum >= 7L ||
                    sum >= 5 && answer11_6.get().getScore() == 2L ||
                    sum >= 5 && answer11_7.get().getScore() == 2L);

        } catch (NoSuchElementException nsee) {
            return null;
        }
    }

    @JsonIgnore
    public List<AssessmentNeed> getLayer3SentencePlanNeeds() {

        if (!isLayer3()) {
            return new ArrayList<>();
        }

        Map<String, Map<String, String>> planSections = Map.of(
                "10"
                , Map.of(
                        "harmQuestion", "10.98",
                        "reoffendingQuestion", "10.99"),
                "11"
                , Map.of(
                        "harmQuestion", "11.98",
                        "reoffendingQuestion", "11.99"),
                "12"
                , Map.of(
                        "harmQuestion", "12.98",
                        "reoffendingQuestion", "12.99"),
                "3"
                , Map.of(
                        "harmQuestion", "3.98",
                        "reoffendingQuestion", "3.99"),
                "4"
                , Map.of(
                        "harmQuestion", "4.96",
                        "reoffendingQuestion", "4.98"),
                "5"
                , Map.of(
                        "harmQuestion", "5.98",
                        "reoffendingQuestion", "5.99"),
                "6"
                , Map.of(
                        "harmQuestion", "6.98",
                        "reoffendingQuestion", "6.99"),
                "7"
                , Map.of(
                        "harmQuestion", "7.98",
                        "reoffendingQuestion", "7.99"),
                "8"
                , Map.of(
                        "harmQuestion", "8.97",
                        "reoffendingQuestion", "8.98"),
                "9"
                , Map.of(
                        "harmQuestion", "9.98",
                        "reoffendingQuestion", "9.99")
        );

        List<Section> filteredSections = sections.entrySet().stream().filter(s -> planSections.containsKey(s.getKey())).map(s -> s.getValue()).collect(Collectors.toList());

        List<AssessmentNeed> assessmentNeeds = new ArrayList<>();

        for (Section section : filteredSections) {
            Map<String, String> planSection = planSections.get(section.getRefSectionCode());


            boolean riskReoffending = false;
            boolean riskHarm = riskHarm = Optional.ofNullable(section.getQuestions().get(planSection.get("harmQuestion"))).map(e -> e.getAnswer().get()).map(e -> e.getRefAnswerCode()).orElse("NO").equals("YES");

            try {
                riskReoffending = section.getQuestions().get(planSection.get("reoffendingQuestion")).getAnswer().get().getRefAnswerCode().equals("YES");
            } catch (NullPointerException e) {

            }

            boolean overThreshold = Optional.ofNullable(section.getSectionOtherRawScore()).orElse(0L) >= Optional.ofNullable(section.getRefSection().getRefCrimNeedScoreThreshold()).orElse(Long.MAX_VALUE);
            boolean flagged = Optional.ofNullable(section.getLowScoreAttentionNeeded()).orElse(false);
            if (Stream.of(overThreshold, riskHarm, riskReoffending, flagged).anyMatch(e -> e.equals(true))) {
                assessmentNeeds.add(new AssessmentNeed(section.getRefSection().getShortDescription(), overThreshold, riskHarm, riskReoffending, flagged));
            }
        }
        return assessmentNeeds;
    }

    @JsonIgnore
    public boolean isLayer3() {
        return "LAYER_3".equals(assessmentType);
    }

}
