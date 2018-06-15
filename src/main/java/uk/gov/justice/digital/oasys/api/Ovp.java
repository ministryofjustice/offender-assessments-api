package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Ovp {
    private Long oasysSetId;
    private Long oasysAssessmentGroupId;
    private BigDecimal ovpStaticWeightedScore;
    private BigDecimal ovpDynamicWeightedScore;
    private BigDecimal ovpTotalWeightedScore;
    private BigDecimal ovp1Year;
    private BigDecimal ovp2Year;
    private String ovpRiskSummary;
    private String ovpRisk;
    private BigDecimal ovpPreviousWeightedScore;
    private BigDecimal ovpViolentWeightedScore;
    private BigDecimal ovpNonViolentWeightedScore;
    private BigDecimal ovpAgeWeightedScore;
    private BigDecimal ovpSexWeightedScore;
    private LocalDateTime completedDate;
    private boolean assessmentVoided;
    private boolean assessmentCompleted;
}
