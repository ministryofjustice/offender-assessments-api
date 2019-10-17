package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.transformer.OffenderTransformer;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AuthenticationService {

    private OasysUserRepository oasysUserRepository;

    @Autowired
    public AuthenticationService(OasysUserRepository oasysUserRepository) {
        this.oasysUserRepository = oasysUserRepository;
    }

    public Optional<OasysUserAuthentication> getUserByUserId(String oasysId) {
        return oasysUserRepository.findOasysUserByOasysUserCode(oasysId).map(user -> OasysUserAuthentication.from(user));
    }

    public boolean validateUserCredentials(String username, String password) {
        return Optional.ofNullable(oasysUserRepository.validateCredentials(username, password)).orElse(false);
    }

    public boolean userCanAccessOffenderRecord(String oasysId, Long offenderId) {
        return true;
    }

}
