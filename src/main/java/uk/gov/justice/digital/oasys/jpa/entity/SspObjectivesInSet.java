package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "SSP_OBJECTIVES_IN_SET")
public class SspObjectivesInSet {
    @Id
    @Column(name = "SSP_OBJECTIVES_IN_SET_PK")
    private Long sspObjectivesInSetPk;
    @Column(name = "COPIED_FORWARD_INDICATOR")
    private String copiedForwardIndicator;
    @Column(name = "HOW_PROGRESS_MEASURED")
    private String howProgressMeasured;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "OBJECTIVE_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OBJECTIVE_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement objectiveType;

    @Column(name = "LAST_UPDATE_DATE")
    private Time lastUpdateDate;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CF_LAST_ASSMT_OBJ")
    private Long cfLastAssmtObj;
    @Column(name = "CF_ORIG_ASSMT_OBJ")
    private Long cfOrigAssmtObj;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

    @OneToMany
    @JoinColumn(name = "SSP_OBJECTIVES_IN_SET_PK", referencedColumnName = "SSP_OBJECTIVES_IN_SET_PK")
    private List<SspObjIntervenePivot> sspObjIntervenePivots;

    @OneToOne
    @JoinColumn(name = "SSP_OBJECTIVES_IN_SET_PK", referencedColumnName = "SSP_OBJECTIVES_IN_SET_PK")
    private SspWhoDoWorkPivot sspWhoDoWorkPivot;

    @OneToOne
    @JoinColumn(name = "SSP_OBJECTIVES_IN_SET_PK", referencedColumnName = "SSP_OBJECTIVES_IN_SET_PK")
    private SspObjective sspObjective;

    @OneToOne
    @JoinColumn(name = "SSP_OBJECTIVES_IN_SET_PK", referencedColumnName = "SSP_OBJECTIVES_IN_SET_PK")
    private SspObjectiveMeasure sspObjectiveMeasure;

    @OneToMany
    @JoinColumn(name = "SSP_OBJECTIVES_IN_SET_PK", referencedColumnName = "SSP_OBJECTIVES_IN_SET_PK")
    private List<SspCrimNeedObjPivot> sspCrimNeedObjPivots;

}
