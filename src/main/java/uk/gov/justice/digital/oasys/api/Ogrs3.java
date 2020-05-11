package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.math.BigDecimal;
import java.util.Objects;

@Value
@Builder
public class Ogrs3 {
    BigDecimal ogrs3_1Year;
    BigDecimal ogrs3_2Year;
    RefElementDto reconvictionRisk;

    public static Ogrs3 from(Assessment assessment) {

        if(Objects.isNull(assessment)) {
            return null;
        }

        return new Ogrs3(
                assessment.getOgrs31Year(),
                assessment.getOgrs32Year(),
                RefElementDto.from(assessment.getOgrs3RiskRecon())
        );
    }

}
