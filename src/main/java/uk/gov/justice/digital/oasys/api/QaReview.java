package uk.gov.justice.digital.oasys.api;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QaReview {
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

    private OasysUser qaUser;

}
