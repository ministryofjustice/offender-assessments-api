package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "BASIC_SENTENCE_PLAN_OBJ")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BasicSentencePlanObj {
    @Id
    @Column(name = "BASIC_SENT_PLAN_OBJ_PK")
    @SequenceGenerator(name = "BASIC_SENTENCE_PLAN_OBJ_SEQ", sequenceName = "BASIC_SENTENCE_PLAN_OBJ_SEQ")
    @GeneratedValue(generator = "BASIC_SENTENCE_PLAN_OBJ_SEQ", strategy = GenerationType.SEQUENCE)
    private Long basicSentPlanObjPk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "INCLUDE_IN_PLAN_IND")
    private String includeInPlanInd;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OFFENCE_BEHAV_LINK_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OFFENCE_BEHAV_LINK_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement offenceBehaviourLink;


    @Column(name = "OBJECTIVE_TEXT")
    private String objectiveText;
    @Column(name = "MEASURE_TEXT")
    private String measureText;
    @Column(name = "WHAT_WORK_TEXT")
    private String whatWorkText;
    @Column(name = "WHO_WILL_DO_WORK_TEXT")
    private String whoWillDoWorkText;
    @Column(name = "TIMESCALES_TEXT")
    private String timescalesText;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "DATE_OPENED")
    private LocalDateTime dateOpened;
    @Column(name = "DATE_COMPLETED")
    private LocalDateTime dateCompleted;
    @Column(name = "PROBLEM_AREA_COMP_IND")
    private String problemAreaCompInd;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;
    @Column(name = "CF_LAST_BCS_INT")
    private Long cfLastBcsInt;
    @Column(name = "CF_ORIG_BCS_INT")
    private Long cfOrigBcsInt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BasicSentencePlanObj))
            return false;

        BasicSentencePlanObj other = (BasicSentencePlanObj) o;
        return getBasicSentPlanObjPk() != null &&
                getBasicSentPlanObjPk().equals(other.getBasicSentPlanObjPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
