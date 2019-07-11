package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public
class AssessmentNeed {
    private String name;
    private Boolean overThreshold;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean flaggedAsNeed;
}
