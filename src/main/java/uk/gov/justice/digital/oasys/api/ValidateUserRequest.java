package uk.gov.justice.digital.oasys.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidateUserRequest {
    private String user;
    private String password;
}
