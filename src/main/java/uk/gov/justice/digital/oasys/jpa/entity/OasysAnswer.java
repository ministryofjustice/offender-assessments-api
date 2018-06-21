package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;

@Data
@ToString(exclude = "oasysQuestion")
@Entity
@Table(name = "OASYS_ANSWER")
public class OasysAnswer {
    @Id
    @Column(name = "OASYS_ANSWER_PK")
    private Long oasysAnswerPk;
//    @Column(name = "OASYS_QUESTION_PK")
//    private Long oasysQuestionPk;
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

    @OneToOne
    @JoinColumn(name = "OASYS_QUESTION_PK")
    private OasysQuestion oasysQuestion;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER"),
            @JoinColumn(name = "REF_SECTION_CODE", referencedColumnName = "REF_SECTION_CODE"),
            @JoinColumn(name = "REF_QUESTION_CODE", referencedColumnName = "REF_QUESTION_CODE"),
            @JoinColumn(name = "REF_ANSWER_CODE", referencedColumnName = "REF_ANSWER_CODE")
    })
    private RefAnswer refAnswer;


}
