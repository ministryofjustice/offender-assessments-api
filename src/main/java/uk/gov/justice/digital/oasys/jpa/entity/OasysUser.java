package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Getter
@Entity
@Table(name = "OASYS_USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OasysUser {
    @Column(name = "OASYS_USER_UK")
    private Long oasysUserUk;
    @Id
    @Column(name = "OASYS_USER_CODE")
    private String oasysUserCode;
    @Column(name = "USER_FORENAME_1")
    private String userForename1;
    @Column(name = "USER_FORENAME_2")
    private String userForename2;
    @Column(name = "USER_FORENAME_3")
    private String userForename3;
    @Column(name = "USER_FAMILY_NAME")
    private String userFamilyName;
    @Column(name = "LAST_LOGIN")
    private Time lastLogin;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "USER_STATUS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "USER_STATUS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement userStatus;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "OASYS_USER_CODE", referencedColumnName = "OASYS_USER_CODE")
    private List<AreaEstUserRole> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OasysUser))
            return false;

        OasysUser other = (OasysUser) o;
        return getOasysUserCode() != null &&
                getOasysUserCode().equals(other.getOasysUserCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
