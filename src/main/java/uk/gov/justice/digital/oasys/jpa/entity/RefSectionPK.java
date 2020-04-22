package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefSectionPK implements Serializable {
    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    @Id
    private String versionNumber;
    @Column(name = "REF_SECTION_CODE")
    @Id
    private String refSectionCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefSectionPK)) return false;
        RefSectionPK that = (RefSectionPK) o;
        return Objects.nonNull(getRefAssVersionCode()) &&
                Objects.nonNull(getVersionNumber()) &&
                Objects.nonNull(getRefSectionCode()) &&
                Objects.equals(getRefAssVersionCode(), that.getRefAssVersionCode()) &&
                Objects.equals(getVersionNumber(), that.getVersionNumber()) &&
                Objects.equals(getRefSectionCode(), that.getRefSectionCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
