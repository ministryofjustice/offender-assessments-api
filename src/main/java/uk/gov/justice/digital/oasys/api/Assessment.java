package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class Assessment {
    private Long oasysSetId;
    private String assessmentType;
    private String historicStatus;
    private String assessmentStatus;
    private AssessmentVersion assessmentVersion;
    private LocalDateTime createdDateTime;
    private boolean completed;
    private LocalDateTime completedDateTime;
    private boolean voided;
    private List<Section> sections;
}
