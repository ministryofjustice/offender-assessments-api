package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentSummary {
    @JsonProperty("oasysSetId")
    private Long oasysSetId;
    @JsonProperty("assessmentType")
    private String assessmentType;
    @JsonProperty("historicStatus")
    private String historicStatus;
    @JsonProperty("assessmentStatus")
    private String assessmentStatus;
    @JsonProperty("assessmentVersion")
    private AssessmentVersionDto assessmentVersion;
    @JsonProperty("createdDateTime")
    private LocalDateTime createdDateTime;
    @JsonProperty("completed")
    private boolean completed;
    @JsonProperty("completedDateTime")
    private LocalDateTime completedDateTime;
    @JsonProperty("voidedDateTime")
    private boolean voided;

    public static Set<AssessmentSummary> from(Stream<OasysSet> oasysSets) {
        return oasysSets.map(AssessmentSummary::from).collect(Collectors.toSet());
    }

    private static AssessmentSummary from(OasysSet oasysSet) {

        return new AssessmentSummary(
                oasysSet.getOasysSetPk(),
                oasysSet.getAssessmentType().getRefElementCode(),
                oasysSet.getGroup().getHistoricStatusELm(),
                oasysSet.getAssessmentStatus().getRefElementCode(),
                oasysSet.getRefAssVersion() == null ? null : AssessmentVersionDto.from(oasysSet.getRefAssVersion()),
                oasysSet.getCreateDate() == null ? null : oasysSet.getCreateDate().toLocalDateTime(),
                oasysSet.getDateCompleted() == null ? false : true,
                oasysSet.getDateCompleted() == null ? null : oasysSet.getDateCompleted().toLocalDateTime(),
                oasysSet.getAssessmentVoidedDate() == null ? false : true);

    }
}