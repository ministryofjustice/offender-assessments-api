package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
public class RefSectionDto {
    Long refSectionId;
    String refSectionCode;
    String shortDescription;
    String description;
    Long refCrimNeedScoreThreshold;
    boolean refScoredForOgp;
    boolean refScoredForOvp;
    Collection<RefQuestionDto> refQuestions;

    public static Collection<RefSectionDto> from(Collection<RefSection> refSections) {
        return Optional.ofNullable(refSections)
                .map(refSectionList -> refSections
                        .stream()
                        .map(RefSectionDto::from)
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    public static RefSectionDto from(RefSection refSection) {

        return new RefSectionDto(
                refSection.getCrimNeedScoreThreshold(),
                refSection.getRefSectionCode(),
                shortDescriptionOf(refSection.getSectionType()),
                descriptionOf(refSection.getSectionType()),
                refSection.getCrimNeedScoreThreshold(),
                DtoUtils.ynToBoolean(refSection.getScoredForOgp()),
                DtoUtils.ynToBoolean(refSection.getScoredForOgp()),
                RefQuestionDto.from(refSection.getRefQuestions()));
    }

    private static String descriptionOf(RefElement sectionType) {
        return Optional.ofNullable(sectionType).map(RefElement::getRefElementDesc).orElse(null);
    }

    private static String shortDescriptionOf(RefElement sectionType) {
        return Optional.ofNullable(sectionType).map(RefElement::getRefElementShortDesc).orElse(null);
    }
}
