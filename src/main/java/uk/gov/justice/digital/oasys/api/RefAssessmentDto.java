package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;

import java.util.Collection;

@Value
public class RefAssessmentDto {
    Long refAssessmentVersionId;
    String refAssVersionCode;
    String versionNumber;
    Long oasysScoringAlgorithmVersion;
    String refModuleCode;
    Collection<RefSectionDto> refSections;

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
