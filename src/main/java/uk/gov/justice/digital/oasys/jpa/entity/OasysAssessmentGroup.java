package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OASYS_ASSESSMENT_GROUP")
public class OasysAssessmentGroup {
    @Id
    @Column(name = "OASYS_ASSESSMENT_GROUP_PK")
    private Long oasysAssessmentGroupPk;
    @Column(name = "OFFENDER_PK")
    private Long offenderPk;
    @Column(name = "ASSESSMENT_DATE_CLOSED")
    private Time assessmentDateClosed;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Timestamp lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;
    @Column(name = "HISTORIC_STATUS_ELM")
    private String historicStatusELm;
    @Column(name = "HISTORIC_STATUS_CAT")
    private String historicStatusCat;

    @OneToMany
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK", referencedColumnName = "OASYS_ASSESSMENT_GROUP_PK")
    private List<OasysSet> oasysSets;

}
