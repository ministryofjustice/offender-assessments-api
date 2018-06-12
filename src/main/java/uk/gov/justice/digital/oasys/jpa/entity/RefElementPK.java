package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RefElementPK implements Serializable {
    private String refCategoryCode;
    private String refElementCode;

    @Column(name = "REF_CATEGORY_CODE")
    @Id
    public String getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(String refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    @Column(name = "REF_ELEMENT_CODE")
    @Id
    public String getRefElementCode() {
        return refElementCode;
    }

    public void setRefElementCode(String refElementCode) {
        this.refElementCode = refElementCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefElementPK that = (RefElementPK) o;
        return Objects.equals(refCategoryCode, that.refCategoryCode) &&
                Objects.equals(refElementCode, that.refElementCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(refCategoryCode, refElementCode);
    }
}
