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
public class RefElementPK implements Serializable {
    @Column(name = "REF_CATEGORY_CODE")
    @Id
    private String refCategoryCode;
    @Column(name = "REF_ELEMENT_CODE")
    @Id
    private String refElementCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefElementPK)) return false;
        RefElementPK that = (RefElementPK) o;
        return Objects.nonNull(getRefCategoryCode()) &&
                Objects.nonNull(getRefElementCode()) &&
                Objects.equals(getRefCategoryCode(), that.getRefCategoryCode()) &&
                Objects.equals(getRefElementCode(), that.getRefElementCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
