package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class AssessmentSummaryDto {
    Long assessmentId;
    String refAssessmentVersionCode;
    String refAssessmentVersionNumber;
    Long refAssessmentId;
    String assessmentType;
    String assessmentStatus;
    String historicStatus;
    Long refAssessmentOasysScoringAlgorithmVersion;
    String assessorName;
    LocalDateTime createdDateTime;
    LocalDateTime completedDateTime;
    LocalDateTime voidedDateTime;

    public static Collection<AssessmentSummaryDto> from(Collection<Assessment> assessmentSummaries) {
        return Optional.ofNullable(assessmentSummaries)
                .map(as -> as
                        .stream()
                        .filter(Objects::nonNull)
                        .map(AssessmentSummaryDto::from)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    private static AssessmentSummaryDto from(Assessment assessmentSummary) {
        var assessmentVersion = assessmentSummary.getAssessmentVersion();
        return new AssessmentSummaryDto(
                assessmentSummary.getOasysSetPk(),
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getRefAssVersionCode() : null,
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getVersionNumber() : null,
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getRefAssVersionUk() : null,
                assessmentSummary.getAssessmentType(),
                assessmentSummary.getAssessmentStatus(),
                assessmentSummary.getGroup().getHistoricStatus(),
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getOasysScoringAlgVersion() : null,
                assessmentSummary.getAssessorName(),
                assessmentSummary.getCreateDate(),
                assessmentSummary.getDateCompleted(),
                assessmentSummary.getAssessmentVoidedDate());
    }
}