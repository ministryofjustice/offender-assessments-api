package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OasysUserDto {
    private String oasysUserId;
    private String userName;

    public static OasysUserDto from(OasysUser oasysUser) {
        if (oasysUser == null) {
            return null;
        }
        return new OasysUserDto(oasysUser.getOasysUserCode(), String.format("%s %s", oasysUser.getUserForename1(), oasysUser.getUserFamilyName()));
    }
}
