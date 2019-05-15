package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public boolean isLayer3() {
        return "LAYER_3".equals(assessmentType);
    }

}
