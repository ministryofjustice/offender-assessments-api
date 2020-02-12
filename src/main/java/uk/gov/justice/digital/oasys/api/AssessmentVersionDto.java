package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentVersionDto {
    @JsonProperty("refAssVersionId")
    private Long refAssVersionId;
    @JsonProperty("refAssVersionCode")
    private String refAssVersionCode;
    @JsonProperty("versionNumber")
    private String versionNumber;
    @JsonProperty("oasysFormVersion")
    private Long oasysFormVersion;
    @JsonProperty("oasysScoringAlgorithmVersion")
    private Long oasysScoringAlgorithmVersion;
    @JsonProperty("refModuleCode")
    private String refModuleCode;

    public static AssessmentVersionDto from(RefAssVersion refAssVersion) {
        return new AssessmentVersionDto(
                refAssVersion.getRefAssVersionUk(),
                refAssVersion.getRefAssVersionCode(),
                refAssVersion.getVersionNumber(),
                refAssVersion.getOasysFormVersion(),
                refAssVersion.getOasysScoringAlgVersion(),
                refAssVersion.getRefModuleCode()
        );
    }
}