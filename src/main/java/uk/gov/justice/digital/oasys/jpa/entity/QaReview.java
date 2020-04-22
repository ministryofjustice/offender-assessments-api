package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QA_REVIEW")
public class QaReview {
    @Id
    @Column(name = "QA_REVIEW_PK")
    private Long qaReviewPk;
    @Column(name = "QA_SCORE")
    private Long qaScore;
    @Column(name = "QA_GRADING_ELM")
    private String qaGrading;
    @Column(name = "QA_STATUS_ELM")
    private String qaStatus;
    @Column(name = "DATE_SELECTED")
    private LocalDateTime dateSelected;
    @Column(name = "DATE_COMPLETED")
    private LocalDateTime dateCompleted;
    @Column(name = "QA_SUBSTITUTION_REASON")
    private String qaSubstitutionReason;
//    @Column(name = "OASYS_SET_PK")

    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Column(name = "REF_PERIOD_YYYY")
    private Long refPeriodYear;
    @Column(name = "REF_PERIOD_QTR")
    private Long refPeriodQtr;
    @Column(name = "REF_PERIOD_MM")
    private Long refPeriodMonth;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "CURRENTLY_HIDDEN_IND")
    private String currentlyHidden;

    @OneToOne
    @JoinColumn(name = "OASYS_USER_CODE", referencedColumnName = "OASYS_USER_CODE")
    private OasysUser qaUser;

    @OneToOne
    @JoinColumn(name = "OASYS_SET_PK")
    private OasysSet oasysSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof QaReview))
            return false;

        QaReview other = (QaReview) o;
        return getQaReviewPk() != null &&
                getQaReviewPk().equals(other.getQaReviewPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
