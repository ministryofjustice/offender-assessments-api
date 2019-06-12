package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REF_ANSWER")
@IdClass(RefAnswerPK.class)
public class RefAnswer {
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
    @Id
    @Column(name = "REF_ANSWER_CODE")
    private String refAnswerCode;
    @Column(name = "REF_ANSWER_UK")
    private Long refAnswerUk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "REF_SECTION_ANSWER")
    private String refSectionAnswer;
    @Column(name = "DEFAULT_DISPLAY_SCORE")
    private Long defaultDisplayScore;
    @Column(name = "OGP_SCORE")
    private Long ogpScore;
    @Column(name = "OVP_SCORE")
    private Long ovpScore;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "QA_RAW_SCORE")
    private Long qaRawScore;
    @Column(name = "CREATE_DATE")
    private Time createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

}
