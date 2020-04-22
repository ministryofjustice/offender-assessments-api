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
public class RefAssessmentVersionPK implements Serializable {
    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    @Id
    private String versionNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefAssessmentVersionPK)) return false;
        RefAssessmentVersionPK that = (RefAssessmentVersionPK) o;
        return Objects.nonNull(getRefAssVersionCode()) &&
                Objects.nonNull(getVersionNumber()) &&
                Objects.equals(getRefAssVersionCode(), that.getRefAssVersionCode()) &&
                Objects.equals(getVersionNumber(), that.getVersionNumber());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
