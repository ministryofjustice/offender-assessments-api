package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AssessmentVersion {
    private Long refAssVersionId;
    private String refAssVersionCode;
    private String versionNumber;
    private Long oasysFormVersion;
    private Long oasysScoringAlgorithmVersion;
    private String refModuleCode;
}
