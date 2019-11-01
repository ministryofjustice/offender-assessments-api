package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationStatus {

    private String STATE;

    public boolean isAuthenticated() {
        return STATE.equals("SUCCESS");
    }

}
