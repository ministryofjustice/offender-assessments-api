package uk.gov.justice.digital.oasys.jpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthorisationStatus {
    @JsonProperty("STATE")
    private AuthorisationState state;
}
