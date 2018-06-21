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
@Table(name = "REF_QUESTION")
@IdClass(RefQuestionPK.class)
public class RefQuestion {
    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Id
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Id
    @Column(name = "REF_SECTION_CODE")
    private String refSectionCode;
    @Id
    @Column(name = "REF_QUESTION_CODE")
    private String refQuestionCode;
    @Column(name = "REF_QUESTION_UK")
    private Long refQuestionUk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "REF_SECTION_QUESTION")
    private String refSectionQuestion;
    @Column(name = "MANDATORY_IND")
    private String mandatoryInd;
    @Column(name = "VERSION_NUMBER_PARENT")
    private String versionNumberParent;
    @Column(name = "REF_SECTION_CODE_PARENT")
    private String refSectionCodeParent;
    @Column(name = "REF_QUESTION_CODE_PARENT")
    private String refQuestionCodeParent;
    @Column(name = "QA_WEIGHTING")
    private Long qaWeighting;
    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Column(name = "REF_ASS_VERSION_CODE_PARENT")
    private String refAssVersionCodeParent;
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
