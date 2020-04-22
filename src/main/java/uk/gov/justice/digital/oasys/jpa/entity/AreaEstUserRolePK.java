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
public class AreaEstUserRolePK implements Serializable {
    @Column(name = "OASYS_USER_CODE")
    @Id
    private String oasysUserCode;
    @Column(name = "REF_ROLE_CODE")
    @Id
    private String refRoleCode;
    @Column(name = "CT_AREA_EST_CODE")
    @Id
    private String ctAreaEstCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaEstUserRolePK)) return false;
        AreaEstUserRolePK that = (AreaEstUserRolePK) o;
        return Objects.nonNull(getOasysUserCode()) &&
                Objects.nonNull(getRefRoleCode()) &&
                Objects.nonNull(getCtAreaEstCode()) &&
                Objects.equals(getOasysUserCode(), that.getOasysUserCode()) &&
                Objects.equals(getRefRoleCode(), that.getRefRoleCode()) &&
                Objects.equals(getCtAreaEstCode(), that.getCtAreaEstCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
