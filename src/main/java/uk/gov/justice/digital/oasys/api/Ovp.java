package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Ogp {
    private Long oasysSetId;
    private Long oasysAssessmentGroupId;
    private BigDecimal ogpStWesc;
    private BigDecimal ogpDyWesc;
    private BigDecimal ogpTotWesc;
    private BigDecimal ogp1Year;
    private BigDecimal ogp2Year;
    private String riskSummary;
    private String risk;
    private LocalDateTime completedDate;
    private boolean assessmentVoided;
    private boolean assessmentCompleted;
}
