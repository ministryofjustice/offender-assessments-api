package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "REF_ASS_VERSION")
@IdClass(RefAssVersionPK.class)
public class RefAssVersion {
    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Id
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Column(name = "REF_ASS_VERSION_UK")
    private Long refAssVersionUk;
    @Column(name = "START_DATE")
    private Time startDate;
    @Column(name = "END_DATE")
    private Time endDate;
    @Column(name = "OASYS_FORM_VERSION")
    private Long oasysFormVersion;
    @Column(name = "OASYS_SCORING_ALG_VERSION")
    private Long oasysScoringAlgVersion;
    @Column(name = "REF_MODULE_CODE")
    private String refModuleCode;
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
