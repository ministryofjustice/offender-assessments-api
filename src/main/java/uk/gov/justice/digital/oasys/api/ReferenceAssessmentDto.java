package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class ReferenceAssessmentDto {
    private Long refAssVersionId;
    private String refAssVersionCode;
    private String versionNumber;
    private Long oasysFormVersion;
    private Long oasysScoringAlgorithmVersion;
    private String refModuleCode;

    private List<RefSectionDto> refSections;

    public static ReferenceAssessmentDto from(RefAssVersion refAssVersion) {
        return ReferenceAssessmentDto.builder()
                .refAssVersionCode(refAssVersion.getRefAssVersionCode())
                .refAssVersionId(refAssVersion.getRefAssVersionUk())
                .oasysFormVersion(refAssVersion.getOasysFormVersion())
                .oasysScoringAlgorithmVersion(refAssVersion.getOasysScoringAlgVersion())
                .versionNumber(refAssVersion.getVersionNumber())
                .refSections(RefSectionDto.from(refAssVersion.getRefSections()))
                .build();
    }
}
