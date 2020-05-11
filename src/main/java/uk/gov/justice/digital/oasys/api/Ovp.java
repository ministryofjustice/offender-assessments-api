package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.math.BigDecimal;
import java.util.Objects;

@Value
@Builder
public class Ovp {

    BigDecimal ovpStaticWeightedScore;
    BigDecimal ovpDynamicWeightedScore;
    BigDecimal ovpTotalWeightedScore;
    BigDecimal ovp1Year;
    BigDecimal ovp2Year;
    RefElementDto ovpRisk;
    BigDecimal ovpPreviousWeightedScore;
    BigDecimal ovpViolentWeightedScore;
    BigDecimal ovpNonViolentWeightedScore;
    BigDecimal ovpAgeWeightedScore;
    BigDecimal ovpSexWeightedScore;

    public static Ovp from(Assessment assessment) {
        if(Objects.isNull(assessment)) {
            return null;
        }

        return new Ovp(
                assessment.getOvpStWesc(),
                assessment.getOvpDyWesc(),
                assessment.getOvpTotWesc(),
                assessment.getOvp1Year(),
                assessment.getOvp2Year(),
                RefElementDto.from(assessment.getOvpRiskRecon()),
                assessment.getOvpPrevWesc(),
                assessment.getOvpVioWesc(),
                assessment.getOvpNonVioWesc(),
                assessment.getOvpAgeWesc(),
                assessment.getOvpSexWesc()
        );
    }
}

