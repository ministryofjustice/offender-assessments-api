package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OasysUserAuthentication {
    private String UserId;
    private String userName;
    private String email;
    private List<String> roles;
    private List<String> regions;
}
