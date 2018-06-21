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
@Table(name = "REF_SECTION")
@IdClass(RefSectionPK.class)
public class RefSection {
    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Id
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Id
    @Column(name = "REF_SECTION_CODE")
    private String refSectionCode;
    @Column(name = "REF_SECTION_UK")
    private Long refSectionUk;
    @Column(name = "FORM_SEQUENCE")
    private Long formSequence;
    @Column(name = "SECTION_TYPE_ELM")
    private String sectionTypeElm;
    @Column(name = "SECTION_TYPE_CAT")
    private String sectionTypeCat;
    @Column(name = "CRIM_NEED_SCORE_THRESHOLD")
    private Long crimNeedScoreThreshold;
    @Column(name = "SCORED_FOR_OGP")
    private String scoredForOgp;
    @Column(name = "SCORED_FOR_OVP")
    private String scoredForOvp;
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
