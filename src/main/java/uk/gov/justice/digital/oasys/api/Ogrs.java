package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ogrs {
    private Long oasysSetId;
    private Long oasysAssessmentGroupId;
    private BigDecimal ogrs3_1_year;
    private BigDecimal ogrs3_2_year;
}
