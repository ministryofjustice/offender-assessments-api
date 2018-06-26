package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AssessmentSummary {
    private Long oasysSetId;
    private String assessmentType;
    private String historicStatus;
    private String assessmentStatus;
    private AssessmentVersion assessmentVersion;
    private LocalDateTime createdDateTime;
    private boolean completed;
    private LocalDateTime completedDateTime;
    private boolean voided;
}
