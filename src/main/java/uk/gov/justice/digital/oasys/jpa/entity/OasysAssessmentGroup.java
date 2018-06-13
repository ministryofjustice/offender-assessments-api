package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "OASYS_ASSESSMENT_GROUP")
public class OasysAssessmentGroup {
    @Id
    @Column(name = "OASYS_ASSESSMENT_GROUP_PK")
    private Long oasysAssessmentGroupPk;
    @Column(name = "ASSESSMENT_DATE_CLOSED")
    private Time assessmentDateClosed;
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

    @OneToMany
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK", referencedColumnName = "OASYS_ASSESSMENT_GROUP_PK")
    private List<OasysSet> oasysSets;

}
