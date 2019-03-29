package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReferenceAssessment {
    private Long refAssVersionId;
    private String refAssVersionCode;
    private String versionNumber;
    private Long oasysFormVersion;
    private Long oasysScoringAlgorithmVersion;
    private String refModuleCode;

    private List<RefSection> refSections;
}
