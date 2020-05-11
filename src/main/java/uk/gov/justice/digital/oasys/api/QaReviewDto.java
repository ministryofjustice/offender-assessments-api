package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.QaReview;
import java.time.LocalDateTime;
import java.util.Optional;

import static uk.gov.justice.digital.oasys.api.DtoUtils.ynToBoolean;

@Value
@Builder
public class QaReviewDto {
    Long qaScore;
    String qaGrading;
    String qaStatus;
    LocalDateTime dateSelected;
    LocalDateTime dateCompleted;
    String qaSubstitutionReason;
    Long refPeriodYear;
    Long refPeriodQtr;
    Long refPeriodMonth;
    Long displaySort;
    boolean currentlyHidden;
    OasysUserDto qaUser;

    public static QaReviewDto from(QaReview qaReview) {
        return Optional.ofNullable(qaReview)
                .map(qaReview1 -> QaReviewDto.builder()
                        .currentlyHidden(ynToBoolean(qaReview.getCurrentlyHidden()))
                        .dateCompleted(qaReview.getDateCompleted())
                        .dateSelected(qaReview.getDateSelected())
                        .displaySort(qaReview.getDisplaySort())
                        .qaGrading(qaReview.getQaGrading())
                        .qaScore(qaReview.getQaScore())
                        .qaStatus(qaReview.getQaStatus())
                        .qaSubstitutionReason(qaReview.getQaSubstitutionReason())
                        .qaUser(OasysUserDto.from(qaReview.getQaUser()))
                        .refPeriodMonth(qaReview.getRefPeriodMonth())
                        .refPeriodQtr(qaReview.getRefPeriodQtr())
                        .refPeriodYear(qaReview.getRefPeriodYear())
                        .build()
                )
                .orElse(null);
    }
}