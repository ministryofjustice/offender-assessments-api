package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefSectionDto {
    @JsonProperty("refSectionId")
    private Long refSectionId;
    @JsonProperty("refSectionCode")
    private String refSectionCode;
    @JsonProperty("shortDescription")
    private String shortDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("refCrimNeedScoreThreshold")
    private Long refCrimNeedScoreThreshold;
    @JsonProperty("scoredForOgp")
    private boolean refScoredForOgp;
    @JsonProperty("scoredForOvp")
    private boolean refScoredForOvp;

    @JsonProperty("refQuestions")
    private Collection<RefQuestionDto> refQuestions;

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
