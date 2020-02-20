package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.QaReview;

import java.time.LocalDateTime;
import java.util.Optional;

import static uk.gov.justice.digital.oasys.api.DtoUtils.ynToBoolean;

@Data
@Builder
public class QaReviewDto {
    private Long qaScore;
    private String qaGrading;
    private String qaStatus;
    private LocalDateTime dateSelected;
    private LocalDateTime dateCompleted;
    private String qaSubstitutionReason;
    private Long refPeriodYear;
    private Long refPeriodQtr;
    private Long refPeriodMonth;
    private Long displaySort;
    private boolean currentlyHidden;

    private OasysUserDto qaUser;

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