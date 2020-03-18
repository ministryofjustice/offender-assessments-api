package uk.gov.justice.digital.oasys.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssessmentNeed {

    private SectionHeader section;
    private String name;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean overThreshold;
    private Boolean flaggedAsNeed;

    public boolean anyRiskFlagged() {
        return Boolean.TRUE.equals(riskOfHarm) || Boolean.TRUE.equals(riskOfReoffending) || Boolean.TRUE.equals(overThreshold) || Boolean.TRUE.equals(flaggedAsNeed);
    }

}
