package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Setter
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
    @Setter
    private Long ogpScore;
    @Column(name = "OVP_SCORE")
    @Setter
    private Long ovpScore;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "QA_RAW_SCORE")
    private Long qaRawScore;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

    public static Long getScore(Long ogpScore, Long ovpScore) {
        if(ogpScore != null) {
            return ogpScore;
        } else {
            return ovpScore;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefAnswer)) return false;
        RefAnswer that = (RefAnswer) o;
        return Objects.nonNull(getRefAssVersionCode()) &&
                Objects.nonNull(getVersionNumber()) &&
                Objects.nonNull(getRefSectionCode()) &&
                Objects.nonNull(getRefQuestionCode()) &&
                Objects.nonNull(getRefAnswerCode()) &&
                Objects.equals(getRefAssVersionCode(), that.getRefAssVersionCode()) &&
                Objects.equals(getVersionNumber(), that.getVersionNumber()) &&
                Objects.equals(getRefSectionCode(),that.getRefSectionCode()) &&
                Objects.equals(getRefQuestionCode(), that.getRefQuestionCode()) &&
                Objects.equals(getRefAnswerCode(), that.getRefAnswerCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
