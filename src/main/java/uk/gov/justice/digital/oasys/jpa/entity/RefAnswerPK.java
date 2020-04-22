package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefAnswerPK implements Serializable {
    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    @Id
    private String versionNumber;
    @Column(name = "REF_SECTION_CODE")
    @Id
    private String refSectionCode;
    @Column(name = "REF_QUESTION_CODE")
    @Id
    private String refQuestionCode;
    @Column(name = "REF_ANSWER_CODE")
    @Id
    private String refAnswerCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefAnswerPK)) return false;
        RefAnswerPK that = (RefAnswerPK) o;
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
