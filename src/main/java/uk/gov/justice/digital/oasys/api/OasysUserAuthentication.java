package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OasysUserAuthentication {
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> regions;
    private boolean enabled;
}
