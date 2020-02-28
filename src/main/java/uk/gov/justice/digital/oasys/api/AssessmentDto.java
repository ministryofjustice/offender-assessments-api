package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Value
@Builder(access = AccessLevel.PROTECTED)
public class AssessmentDto {
    private Long oasysSetId;
    private String assessmentType;
    private String historicStatus;
    private String assessmentStatus;
    private AssessmentVersionDto assessmentVersion;
    private LocalDateTime createdDateTime;
    private boolean completed;
    private LocalDateTime completedDateTime;
    private boolean voided;
    private Map<String, SectionDto> sections;
    private Collection<OasysBcsPartDto> oasysBcsParts;
    private QaReviewDto qaReview;
    private Boolean childSafeguardingIndicated;
    private Collection<AssessmentNeedDto> layer3SentencePlanNeeds;

    public static AssessmentDto from(OasysSet oasysSet, Boolean childSafeguardingIndicated, Stream<AssessmentNeed> needs) {
        return AssessmentDto.builder()
                .createdDateTime(oasysSet.getCreateDate())
                .assessmentType(Optional.ofNullable(oasysSet.getAssessmentType()).map(RefElement::getRefElementCode).orElse(null))
                .assessmentVersion(oasysSet.getRefAssVersion() == null ? null : AssessmentVersionDto.from(oasysSet.getRefAssVersion()))
                .completed(Optional.ofNullable(oasysSet.getDateCompleted()).isPresent())
                .completedDateTime(oasysSet.getDateCompleted())
                .oasysSetId(oasysSet.getOasysSetPk())
                .oasysBcsParts(OasysBcsPartDto.from(oasysSet.getOasysBcsParts()))
                .qaReview(QaReviewDto.from(oasysSet.getQaReview()))
                .sections(SectionDto.from(oasysSet.getOasysSections()))
                .voided(Optional.ofNullable(oasysSet.getAssessmentVoidedDate()).isPresent())
                .historicStatus(oasysSet.getGroup().getHistoricStatusELm())
                .assessmentStatus(Optional.ofNullable(oasysSet.getAssessmentStatus()).map(RefElement::getRefElementCode).orElse(null))
                .childSafeguardingIndicated(childSafeguardingIndicated)
                .layer3SentencePlanNeeds(AssessmentNeedDto.from(needs))
                .build();
    }
}
