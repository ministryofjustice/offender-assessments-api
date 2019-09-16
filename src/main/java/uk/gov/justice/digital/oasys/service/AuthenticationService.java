package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.transformer.OffenderTransformer;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    public AuthenticationService() {

    }

    public Optional<OasysUserAuthentication> getUserByUserId(Long oasysId) {
        return Optional.ofNullable(
                new OasysUserAuthentication("1234",
                "1234",
                "John",
                "Smith",
                "test@digital.justice.gov.uk",
                List.of("Region 1"),
                        true));
    }

    public boolean getUserAuthentication(Long oasysId, Long offenderId) {
        return true;
    }

    public boolean validateUser(String username, String password) {
        return true;
    }
}
