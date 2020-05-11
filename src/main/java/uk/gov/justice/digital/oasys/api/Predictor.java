package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;

import java.time.LocalDateTime;
import java.util.Objects;

@Value
@Builder
public class Predictor {
    Long oasysSetId;
    String refAssessmentVersionCode;
    String refAssessmentVersionNumber;
    Long refAssessmentId;
    LocalDateTime completedDate;
    LocalDateTime voidedDateTime;
    boolean assessmentCompleted;
    RefElementDto otherRisk;
    Ogrs3 ogr3;
    Ovp ovp;
    Ogp ogp;

    public static Predictor from(Assessment assessment) {

        if(Objects.isNull(assessment)) {
            return null;
        }

        var assessmentVersion = assessment.getAssessmentVersion();
        return new Predictor(
                assessment.getOasysSetPk(),
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getRefAssVersionCode() : null,
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getVersionNumber() : null,
                Objects.nonNull(assessmentVersion) ? assessmentVersion.getRefAssVersionUk() : null,
                assessment.getDateCompleted(),
                assessment.getAssessmentVoidedDate(),
                Objects.nonNull(assessment.getDateCompleted()),
                RefElementDto.from(assessment.getOtherRiskRecon()),
                Ogrs3.from(assessment),
                Ovp.from(assessment),
                Ogp.from(assessment));
    }

}
