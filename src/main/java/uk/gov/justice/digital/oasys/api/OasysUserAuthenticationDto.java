package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class OasysUserAuthenticationDto {
    String userId;
    String userName;
    String firstName;
    String lastName;
    String email;
    Set<String> regions;
    boolean enabled;

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
