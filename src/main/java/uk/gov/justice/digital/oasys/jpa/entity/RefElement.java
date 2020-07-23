package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REF_ELEMENT")
@IdClass(RefElementPK.class)
public class RefElement {
    @Column(name = "REF_ELEMENT_UK")
    private Long refElementUk;
    @Id
    @Column(name = "REF_CATEGORY_CODE")
    private String refCategoryCode;
    @Id
    @Column(name = "REF_ELEMENT_CODE")
    private String refElementCode;
    @Column(name = "REF_ELEMENT_CTID")
    private String refElementCtid;
    @Column(name = "REF_ELEMENT_SHORT_DESC")
    private String refElementShortDesc;
    @Column(name = "REF_ELEMENT_DESC")
    private String refElementDesc;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefElement)) return false;
        RefElement that = (RefElement) o;
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
