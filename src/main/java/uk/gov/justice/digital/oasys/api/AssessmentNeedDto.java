package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public
class AssessmentNeedDto {


    private String name;
    private Boolean overThreshold;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean flaggedAsNeed;
    @JsonIgnore
    private Boolean anyFlagged;

    public static List<AssessmentNeedDto> from(Collection<SectionDto> sections, Map<String, Map<String, String>> planSections) {
        return Optional.ofNullable(sections)
                .map(section -> sections
                        .stream()
                        .map(a -> AssessmentNeedDto.from(a, planSections))
                        .filter(AssessmentNeedDto::getAnyFlagged)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private static AssessmentNeedDto from(SectionDto section, Map<String, Map<String, String>> planSections) {
        var planSection = Optional.ofNullable(planSections.get(section.getRefSection().getRefSectionCode())).orElse(new HashMap<>());

        var riskHarm = sectionHasRiskOfHarm(section, planSection);
        var riskReoffending = sectionHasRiskOfReoffending(section, planSection);
        var overThreshold = sectionIsOverThreshold(section);
        var flaggedAsNeed = Optional.ofNullable(section.getLowScoreAttentionNeeded());
        var anyFlagged = Stream.of(overThreshold, riskHarm, riskReoffending, flaggedAsNeed).filter(Optional::isPresent).map(Optional::get).anyMatch(e -> e.equals(true));

        return new AssessmentNeedDto(
                section.getRefSection().getShortDescription(),
                overThreshold.orElse(null),
                riskHarm.orElse(null),
                riskReoffending.orElse(null),
                flaggedAsNeed.orElse(null),
                anyFlagged
                );
    }

    private static Optional<Boolean> sectionIsOverThreshold(SectionDto section) {
        return Optional.ofNullable(section.getSectionOtherRawScore()).map(score -> score >= Optional.ofNullable(section.getRefSection()).map(RefSectionDto::getRefCrimNeedScoreThreshold).orElse(Long.MAX_VALUE));
    }

    private static Optional<Boolean> sectionHasRiskOfReoffending(SectionDto section, Map<String, String> planSection) {
        return questionHasAnswer(section.getQuestions().get(planSection.get("reoffendingQuestion")));
    }

    private static Optional<Boolean> sectionHasRiskOfHarm(SectionDto section, Map<String, String> planSection) {
        return questionHasAnswer(section.getQuestions().get(planSection.get("harmQuestion")));
    }

    private static Optional<Boolean> questionHasAnswer(QuestionDto question) {
        return Optional.ofNullable(question)
                .flatMap(q -> Optional.ofNullable(q.getAnswer()))
                .flatMap(a -> Optional.ofNullable(a.getRefAnswerCode()))
                .map("YES"::equals);
    }

}
