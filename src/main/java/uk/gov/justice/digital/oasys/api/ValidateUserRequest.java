package uk.gov.justice.digital.oasys.api;

import lombok.Value;

@Value
public class ValidateUserRequest {
    String user;
    String password;
}
