package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import java.util.Objects;

@Value
public class OasysUserDto {
    String oasysUserId;
    String userName;

    public static OasysUserDto from(OasysUser oasysUser) {
        if (Objects.isNull(oasysUser)) {
            return null;
        }
        return new OasysUserDto(oasysUser.getOasysUserCode(), String.format("%s %s", oasysUser.getUserForename1(), oasysUser.getUserFamilyName()));
    }
}
