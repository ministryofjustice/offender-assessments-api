package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.math.BigDecimal;
import java.util.Objects;

@Value
@Builder
public class Ogp {

    BigDecimal ogpStaticWeightedScore;
    BigDecimal ogpDynamicWeightedScore;
    BigDecimal ogpTotalWeightedScore;
    BigDecimal ogp1Year;
    BigDecimal ogp2Year;
    RefElementDto ogpRisk;

    public static Ogp from(Assessment assessment) {

        if(Objects.isNull(assessment)) {
            return null;
        }

        return new Ogp(
                assessment.getOgpStWesc(),
                assessment.getOgpDyWesc(),
                assessment.getOgpTotWesc(),
                assessment.getOgp1Year(),
                assessment.getOgp2Year(),
                RefElementDto.from(assessment.getOgpRiskRecon())

        );
    }

}
