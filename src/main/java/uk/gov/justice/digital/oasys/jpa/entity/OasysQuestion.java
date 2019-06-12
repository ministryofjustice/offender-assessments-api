package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OASYS_QUESTION")
public class OasysQuestion {
    @Id
    @Column(name = "OASYS_QUESTION_PK")
    private Long oasysQuestionPk;
    @Column(name = "ADDITIONAL_NOTE")
    private String additionalNote;
    @Column(name = "OASYS_SECTION_PK")
    private Long oasysSectionPk;
    @Column(name = "FREE_FORMAT_ANSWER")
    private String freeFormatAnswer;
    @Column(name = "DISPLAY_SCORE")
    private Long displayScore;
    @Column(name = "DISCLOSED_IND")
    private String disclosedInd;
    @Column(name = "CURRENTLY_HIDDEN_IND")
    private String currentlyHiddenInd;
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
    @JoinColumns({
     @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
     @JoinColumn(name = "REF_QUESTION_CODE", referencedColumnName = "REF_QUESTION_CODE"),
     @JoinColumn(name = "REF_SECTION_CODE", referencedColumnName = "REF_SECTION_CODE"),
     @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER")
    })
    private RefQuestion refQuestion;

    @OneToOne(mappedBy = "oasysQuestion")
    private OasysAnswer oasysAnswer;
}
