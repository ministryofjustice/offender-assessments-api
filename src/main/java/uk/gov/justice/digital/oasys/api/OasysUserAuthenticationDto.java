package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OasysUserAuthenticationDto {
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> regions;
    private boolean enabled;


    public static OasysUserAuthenticationDto from(OasysUser oasysUser) {

        boolean enabled = oasysUser.getUserStatus().getRefElementCode().equals("ACTIVE");

        Set<String> regions = oasysUser.getRoles().stream().map(AreaEstUserRole::getCtAreaEstCode).limit(10).collect(Collectors.toSet());

        return new OasysUserAuthenticationDto(
                oasysUser.getOasysUserCode(),
                oasysUser.getOasysUserCode(),
                oasysUser.getUserForename1(),
                oasysUser.getUserFamilyName(),
                oasysUser.getEmailAddress(),
                regions,
                enabled);

    }
}
