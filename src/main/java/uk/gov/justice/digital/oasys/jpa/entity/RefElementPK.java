package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
public class RefElementPK implements Serializable {
    @Column(name = "REF_CATEGORY_CODE")
    @Id
    private String refCategoryCode;
    @Column(name = "REF_ELEMENT_CODE")
    @Id
    private String refElementCode;

}
