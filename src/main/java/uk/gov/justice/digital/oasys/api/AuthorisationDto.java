package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthorisationDto {
    private String oasysUserCode;
    private Long oasysOffenderId;
    private OffenderPermissionLevel offenderPermissionLevel;
    private OffenderPermissionResource offenderPermissionResource;

    public AuthorisationDto(OffenderPermissionLevel offenderPermissionLevel) {
        this.offenderPermissionLevel = offenderPermissionLevel;
    }
}

