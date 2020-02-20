package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class RefSectionDto {
    private Long refSectionId;
    private String refSectionCode;
    private Long refFormSequence;
    private String shortDescription;
    private String description;
    private Long refCrimNeedScoreThreshold;
    private boolean refScoredForOgp;
    private boolean refScoredForOvp;

    private List<RefQuestionDto> refQuestions;

    public static List<RefSectionDto> from(List<uk.gov.justice.digital.oasys.jpa.entity.RefSection> refSections) {
        return Optional.ofNullable(refSections)
                .map(refSectionList -> refSections
                        .stream()
                        .map(RefSectionDto::from)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public static RefSectionDto from(RefSection refSection) {

        return RefSectionDto.builder()
                .refCrimNeedScoreThreshold(refSection.getCrimNeedScoreThreshold())
                .refFormSequence(refSection.getFormSequence())
                .refScoredForOgp(DtoUtils.ynToBoolean(refSection.getScoredForOgp()))
                .refScoredForOvp(DtoUtils.ynToBoolean(refSection.getScoredForOgp()))
                .refSectionCode(refSection.getRefSectionCode())
                .refSectionId(refSection.getRefSectionUk())
                .shortDescription(shortDescriptionOf(refSection.getSectionType()))
                .description(descriptionOf(refSection.getSectionType()))
                .refQuestions(RefQuestionDto.from(refSection.getRefQuestions()))
                .build();
    }

    private static String descriptionOf(RefElement sectionType) {
        return Optional.ofNullable(sectionType).map(RefElement::getRefElementDesc).orElse(null);
    }

    private static String shortDescriptionOf(RefElement sectionType) {
        return Optional.ofNullable(sectionType).map(RefElement::getRefElementShortDesc).orElse(null);
    }
}
