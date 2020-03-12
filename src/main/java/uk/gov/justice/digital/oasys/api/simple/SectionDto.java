package uk.gov.justice.digital.oasys.api.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.api.QuestionDto;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SectionDto {
    @JsonProperty("sectionId")
    private Long sectionId;
    @JsonProperty("assessmentId")
    private Long assessmentId;
    @JsonProperty("refAssessmentVersionCode")
    private String refAssessmentVersionCode;
    @JsonProperty("refSectionVersionNumber")
    private String refSectionVersionNumber;
    @JsonProperty("refSectionCode")
    private String refSectionCode;
    @JsonProperty("refSectionCrimNeedScoreThreshold")
    private Long refSectionCrimNeedScoreThreshold;
    @JsonProperty("status")
    private String status;
    @JsonProperty("sectionOgpWeightedScore")
    private Long sectionOgpWeightedScore;
    @JsonProperty("sectionOgpRawScore")
    private Long sectionOgpRawScore;
    @JsonProperty("sectionOvpWeightedScore")
    private Long sectionOvpWeightedScore;
    @JsonProperty("sectionOvpRawScore")
    private Long sectionOvpRawScore;
    @JsonProperty("sectionOtherWeightedScore")
    private Long sectionOtherWeightedScore;
    @JsonProperty("sectionOtherRawScore")
    private Long sectionOtherRawScore;
    @JsonProperty("lowScoreAttentionNeeded")
    private String lowScoreAttentionNeeded;
    @JsonProperty("questions")
    private Collection<QuestionDto> questions;

    public static Collection<SectionDto> from(Collection<Section> sections) {
        return Optional.ofNullable(sections)
                .map(s -> s
                        .stream()
                        .filter(Objects::nonNull)
                        .map(SectionDto::from)
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    private static SectionDto from(Section section) {
        var refSection = section.getRefSection();
        return new SectionDto(
                section.getOasysSectionPk(),
                section.getOasysSetPk(),
                Objects.nonNull(refSection) ? refSection.getRefAssVersionCode() : null,
                Objects.nonNull(refSection) ? refSection.getVersionNumber() : null,
                Objects.nonNull(refSection) ? refSection.getRefSectionCode() : null,
                Objects.nonNull(refSection) ? refSection.getCrimNeedScoreThreshold() : null,
                section.getSectionStatusElm(),
                section.getSectOgpWeightedScore(),
                section.getSectOgpRawScore(),
                section.getSectOvpWeightedScore(),
                section.getSectOvpRawScore(),
                section.getSectOtherWeightedScore(),
                section.getSectOtherRawScore(),
                section.getLowScoreNeedAttnInd(),
                QuestionDto.from(section.getOasysQuestions())
        );
    }

}
