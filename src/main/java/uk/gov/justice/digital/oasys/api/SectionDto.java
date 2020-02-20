package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSection;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder
public class SectionDto {
    private Long refSectionId;
    private String refSectionCode;
    private RefSectionDto refSection;
    private Long oasysSectionId;
    private String status;
    private Long sectionOgpWeightedScore;
    private Long sectionOgpRawScore;
    private Long sectionOvpWeightedScore;
    private Long sectionOvpRawScore;
    private Long sectionOtherWeightedScore;
    private Long sectionOtherRawScore;
    private Boolean lowScoreAttentionNeeded;

    private Map<String, QuestionDto> questions;

    public static Map<String, SectionDto> from(Set<OasysSection> oasysSections) {
        return Optional.ofNullable(oasysSections)
                .map(sections -> sections
                        .stream()
                        .map(SectionDto::from)
                        .collect(Collectors.toMap(SectionDto::getRefSectionCode, section -> section)))
                .orElse(null);
    }

    public static SectionDto from(OasysSection section) {
        return SectionDto.builder()
                .refSectionCode(section.getRefSection().getRefSectionCode())
                .oasysSectionId(section.getOasysSectionPk())
                .status(section.getSectionStatusElm())
                .sectionOgpWeightedScore(section.getSectOgpWeightedScore())
                .sectionOgpRawScore(section.getSectOgpRawScore())
                .sectionOvpWeightedScore(section.getSectOvpWeightedScore())
                .sectionOvpRawScore(section.getSectOvpRawScore())
                .sectionOtherWeightedScore(section.getSectOtherWeightedScore())
                .sectionOtherRawScore(section.getSectOtherRawScore())
                .lowScoreAttentionNeeded(DtoUtils.ynToBoolean(section.getLowScoreNeedAttnInd()))
                .questions(QuestionDto.from(section.getOasysQuestions()))
                .refSection(RefSectionDto.from(section.getRefSection()))
                .build();
    }
}
