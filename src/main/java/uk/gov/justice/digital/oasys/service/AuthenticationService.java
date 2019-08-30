package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.transformer.OffenderTransformer;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    public AuthenticationService() {

    }

    public Optional<OasysUserAuthentication> getUserByUserId(Long oasysId) {
        return null;
    }

    public boolean getUserAuthentication(Long oasysId, Long offenderId) {
        return true;
    }

    public boolean validateUser(String username, String password) {
        return true;
    }
}
