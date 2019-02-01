package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Ogp {
    private Long oasysSetId;
    private Long oasysAssessmentGroupId;
    private BigDecimal ogpStaticWeightedScore;
    private BigDecimal ogpDynamicWeightedScore;
    private BigDecimal ogpTotalWeightedScore;
    private BigDecimal ogp1Year;
    private BigDecimal ogp2Year;
    private String ogpRiskSummary;
    private String ogpRisk;
    private LocalDateTime completedDate;
    private boolean assessmentVoided;
    private boolean assessmentCompleted;
}
