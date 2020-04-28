package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Section;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class SectionDto {
    Long sectionId;
    Long assessmentId;
    String refAssessmentVersionCode;
    String refSectionVersionNumber;
    String refSectionCode;
    Long refSectionCrimNeedScoreThreshold;
    String status;
    Long sectionOgpWeightedScore;
    Long sectionOgpRawScore;
    Long sectionOvpWeightedScore;
    Long sectionOvpRawScore;
    Long sectionOtherWeightedScore;
    Long sectionOtherRawScore;
    String lowScoreAttentionNeeded;
    Collection<QuestionDto> questions;

    public static Collection<SectionDto> from(Collection<Section> sections) {
        return Optional.ofNullable(sections)
                .map(s -> s
                        .stream()
                        .filter(Objects::nonNull)
                        .map(SectionDto::from)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
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
