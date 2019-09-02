package uk.gov.justice.digital.oasys.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ValidateUserRequest {

    private String user;

    private String password;
}
