package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "oasysAnswer")
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

    public boolean hasRefQuestion(){
        return refQuestion != null;
    }

    public boolean hasOasysAnswer(){
        return oasysAnswer != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OasysQuestion))
            return false;

        OasysQuestion other = (OasysQuestion) o;
        return getOasysQuestionPk() != null &&
                getOasysQuestionPk().equals(other.getOasysQuestionPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
