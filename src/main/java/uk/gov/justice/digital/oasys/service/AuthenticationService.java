package uk.gov.justice.digital.oasys.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.jpa.entity.AuthenticationStatus;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAuthenticationRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;

import java.io.IOException;
import java.util.Optional;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;
import static com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS;

@Service
public class AuthenticationService {

    private OasysUserRepository oasysUserRepository;
    private OasysAuthenticationRepository oasysAuthenticationRepository;
    private ObjectMapper objectMapper;


    @Autowired
    public AuthenticationService(OasysUserRepository oasysUserRepository, OasysAuthenticationRepository oasysAuthenticationRepository, @Qualifier("globalObjectMapper") ObjectMapper objectMapper) {
        this.oasysUserRepository = oasysUserRepository;
        this.oasysAuthenticationRepository = oasysAuthenticationRepository;
        this.objectMapper = objectMapper;
        objectMapper.enable(ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.enable(UNWRAP_SINGLE_VALUE_ARRAYS);

    }

    public Optional<OasysUserAuthentication> getUserByUserId(String oasysId) {
        return oasysUserRepository.findOasysUserByOasysUserCode(oasysId).map(user -> OasysUserAuthentication.from(user));
    }

    public boolean validateUserCredentials(String username, String password) {

      if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
          return false;
      }

        Optional<String> response = oasysAuthenticationRepository.validateCredentials(username, password);

        if(response.isPresent()) {
            AuthenticationStatus authenticationStatus;
            try {
                return objectMapper.readValue(response.get(), AuthenticationStatus.class).isAuthenticated();
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }

    public boolean userCanAccessOffenderRecord(String oasysId, Long offenderId) {
        return true;
    }

}
