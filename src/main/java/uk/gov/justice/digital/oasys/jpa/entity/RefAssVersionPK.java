package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RefAssVersionPK implements Serializable {
    private String refAssVersionCode;
    private String versionNumber;

    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    public String getRefAssVersionCode() {
        return refAssVersionCode;
    }

    public void setRefAssVersionCode(String refAssVersionCode) {
        this.refAssVersionCode = refAssVersionCode;
    }

    @Column(name = "VERSION_NUMBER")
    @Id
    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefAssVersionPK that = (RefAssVersionPK) o;
        return Objects.equals(refAssVersionCode, that.refAssVersionCode) &&
                Objects.equals(versionNumber, that.versionNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(refAssVersionCode, versionNumber);
    }
}
