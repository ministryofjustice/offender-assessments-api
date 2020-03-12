package uk.gov.justice.digital.oasys.api.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentSummaryDto {
    @JsonProperty("assessmentId")
    private Long assessmentId;
    @JsonProperty("refAssessmentVersionCode")
    private String refAssessmentVersionCode;
    @JsonProperty("refAssessmentVersionNumber")
    private String refAssessmentVersionNumber;
    @JsonProperty("refAssessmentId")
    private Long refAssessmentId;
    @JsonProperty("assessmentType")
    private String assessmentType;
    @JsonProperty("assessmentStatus")
    private String assessmentStatus;
    @JsonProperty("groupHistoricStatus")
    private String historicStatus;
    @JsonProperty("oasysScoringAlgorithmVersion")
    private Long oasysScoringAlgorithmVersion;
    @JsonProperty("assessorName")
    private String assessorName;
    @JsonProperty("created")
    private LocalDateTime createdDateTime;
    @JsonProperty("completed")
    private LocalDateTime completedDateTime;
    @JsonProperty("voided")
    private LocalDateTime voidedDateTime;

    public static Set<AssessmentSummaryDto> from(Collection<Assessment> assessmentSummaries) {
        return assessmentSummaries.stream().map(AssessmentSummaryDto::from).collect(Collectors.toSet());
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