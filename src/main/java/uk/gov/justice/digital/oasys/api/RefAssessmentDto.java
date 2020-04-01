package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;

import java.util.Collection;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefAssessmentDto {
    @JsonProperty("refAssessmentVersionId")
    private Long refAssessmentVersionId;
    @JsonProperty("refVersionCode")
    private String refAssVersionCode;
    @JsonProperty("versionNumber")
    private String versionNumber;
    @JsonProperty("oasysScoringAlgorithmVersion")
    private Long oasysScoringAlgorithmVersion;
    @JsonProperty("refModuleCode")
    private String refModuleCode;

    @JsonProperty("refSections")
    private Collection<RefSectionDto> refSections;

    public static RefAssessmentDto from(RefAssessmentVersion refAssessmentVersion) {
        if (refAssessmentVersion == null) {
            return null;
        }
        return new RefAssessmentDto(
                refAssessmentVersion.getRefAssVersionUk(),
                refAssessmentVersion.getRefAssVersionCode(),
                refAssessmentVersion.getVersionNumber(),
                refAssessmentVersion.getOasysScoringAlgVersion(),
                refAssessmentVersion.getRefModuleCode(),
                RefSectionDto.from(refAssessmentVersion.getRefSections())
        );
    }
}
