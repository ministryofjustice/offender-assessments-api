package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public
class AssessmentNeed {
    private String name;
    private boolean overThreshold;
    private boolean riskOfHarm;
    private boolean riskOfReoffending;
    private boolean flaggedAsNeed;
}
