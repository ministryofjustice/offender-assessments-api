package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefQuestionPK implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefQuestionPK)) return false;
        RefQuestionPK that = (RefQuestionPK) o;
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
