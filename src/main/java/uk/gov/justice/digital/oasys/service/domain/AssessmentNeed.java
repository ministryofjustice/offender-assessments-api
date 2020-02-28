package uk.gov.justice.digital.oasys.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssessmentNeed {

    private Section section;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean overThreshold;
    private Boolean flaggedAsNeed;

    public boolean anyRiskFlagged() {
        return riskOfHarm || riskOfReoffending || overThreshold || flaggedAsNeed;
    }

}
