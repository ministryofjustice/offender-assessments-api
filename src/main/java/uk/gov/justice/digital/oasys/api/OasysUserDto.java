package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;

import java.util.Objects;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OasysUserDto {
    private String oasysUserId;
    private String userName;

    public static OasysUserDto from(OasysUser oasysUser) {
        if (Objects.isNull(oasysUser)) {
            return null;
        }
        return new OasysUserDto(oasysUser.getOasysUserCode(), String.format("%s %s", oasysUser.getUserForename1(), oasysUser.getUserFamilyName()));
    }
}
