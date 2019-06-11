package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "SSP_WHO_DO_WORK_PIVOT")
public class SspWhoDoWorkPivot {
    @Id
    @Column(name = "SSP_WHO_DO_WORK_PIVOT_PK")
    private Long sspWhoDoWorkPivotPk;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "WHO_WORK_OBJ_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "WHO_WORK_OBJ_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement whoDoWork;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "SSP_INTERVENTION_IN_SET_PK")
    private Long sspInterventionInSetPk;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Time createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

}