package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Value
public class AssessmentDto {

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
    Collection<SectionDto> sections;
    //TODO: we don't need these until we get to update the sentence plan code
    //@JsonProperty("oasysBcsParts")
    //private Collection<OasysBcsPartDto> oasysBcsParts;
    //@JsonProperty("qaReview")
    //private QaReviewDto qaReview;
    //TODO: Sentence data shoUld come from Delius or Nomis
    Collection<SentenceDto> sentence;

    Boolean childSafeguardingIndicated;
    Collection<AssessmentNeedDto> layer3SentencePlanNeeds;

    public static AssessmentDto from(Assessment assessmentSummary, Boolean childSafeguardingIndicated, Collection<AssessmentNeed> needs) {
        if (Objects.isNull(assessmentSummary)) {
            return null;
        }
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
                SentenceDto.from(assessmentSummary.getOffenceBlocks()),
                childSafeguardingIndicated,
                AssessmentNeedDto.from(needs));
    }
}