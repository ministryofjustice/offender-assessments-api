package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER"),
            @JoinColumn(name = "REF_SECTION_CODE", referencedColumnName = "REF_SECTION_CODE"),
            @JoinColumn(name = "REF_QUESTION_CODE", referencedColumnName = "REF_QUESTION_CODE")
    })
    private List<RefAnswer> refAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefQuestion)) return false;
        RefQuestion that = (RefQuestion) o;
        return Objects.nonNull(getRefAssVersionCode()) &&
                Objects.nonNull(getVersionNumber()) &&
                Objects.nonNull(getRefSectionCode()) &&
                Objects.nonNull(getRefQuestionCode()) &&
                Objects.equals(getRefAssVersionCode(), that.getRefAssVersionCode()) &&
                Objects.equals(getVersionNumber(), that.getVersionNumber()) &&
                Objects.equals(getRefSectionCode(),that.getRefSectionCode()) &&
                Objects.equals(getRefQuestionCode(), that.getRefQuestionCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
