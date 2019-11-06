package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class OasysUserAuthentication {
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> regions;
    private boolean enabled;


    public static OasysUserAuthentication from(OasysUser oasysUser) {

        boolean enabled = oasysUser.getUserStatus().getRefElementCode().equals("ACTIVE");

        Set<String> regions = oasysUser.getRoles().stream().map(r->r.getCtAreaEstCode()).collect(Collectors.toSet());

        return new OasysUserAuthentication(
                oasysUser.getOasysUserCode(),
                oasysUser.getOasysUserCode(),
                oasysUser.getUserForename1(),
                oasysUser.getUserFamilyName(),
                oasysUser.getEmailAddress(),
                regions,
                enabled);

    }
}
