package uk.gov.justice.digital.oasys.api.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentDto {
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
    @JsonProperty("refAssessmentOasysScoringAlgorithmVersion")
    private Long refAssessmentOasysScoringAlgorithmVersion;
    @JsonProperty("assessorName")
    private String assessorName;
    @JsonProperty("created")
    private LocalDateTime createdDateTime;
    @JsonProperty("completed")
    private LocalDateTime completedDateTime;
    @JsonProperty("voided")
    private LocalDateTime voidedDateTime;
    @JsonProperty("sections")
    private Collection<SectionDto> sections;
    //TODO: we don't need these until we get to update the sentence plan code
    //@JsonProperty("oasysBcsParts")
    //private Collection<OasysBcsPartDto> oasysBcsParts;
    //@JsonProperty("qaReview")
    //private QaReviewDto qaReview;
    //TODO: why specifically childsafeguarding?
    @JsonProperty("childSafeguardingIndicated")
    private Boolean childSafeguardingIndicated;
    @JsonProperty("layer3SentencePlanNeeds")
    private Collection<AssessmentNeedDto> layer3SentencePlanNeeds;

    public static AssessmentDto from(Assessment assessmentSummary, Boolean childSafeguardingIndicated, Collection<AssessmentNeed> needs) {
        var assessmentVersion = assessmentSummary.getAssessmentVersion();
        return new AssessmentDto(
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
                assessmentSummary.getAssessmentVoidedDate(),
                SectionDto.from(assessmentSummary.getOasysSections()),
                childSafeguardingIndicated,
                AssessmentNeedDto.from(needs));
    }
}