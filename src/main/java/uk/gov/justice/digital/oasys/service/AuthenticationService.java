package uk.gov.justice.digital.oasys.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Service
@Slf4j
public class AuthenticationService {

    private OasysUserRepository oasysUserRepository;
    private OasysAuthenticationRepository oasysAuthenticationRepository;
    private ObjectMapper objectMapper;


    @Autowired
    public AuthenticationService(OasysUserRepository oasysUserRepository, OasysAuthenticationRepository oasysAuthenticationRepository) {
        this.oasysUserRepository = oasysUserRepository;
        this.oasysAuthenticationRepository = oasysAuthenticationRepository;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.enable(UNWRAP_SINGLE_VALUE_ARRAYS);
    }

    public Optional<OasysUserAuthentication> getUserByUserId(String username) {
        log.info("GRetrieving user with OASys username {}", username, value(EVENT, USER_AUTHENTICATION));
        return oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(username).map(user -> OasysUserAuthentication.from(user));
    }

    public boolean validateUserCredentials(String username, String password) {
        log.info("Attempting to authenticate user {}", username, value(EVENT, USER_AUTHENTICATION_ATTEMPT));
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
          return false;
        }

        Optional<String> response = oasysAuthenticationRepository.validateCredentials(username, password);

        if(response.isPresent()) {
            try {
                return objectMapper.readValue(response.get(), AuthenticationStatus.class).isAuthenticated();
            } catch (IOException e) {
                log.error("Failed to parse OASys response for user {}", username, value(EVENT, USER_AUTHENTICATION_PARSE_ERROR));
                return false;
            }
        }

        log.info("No response from OASus authentication function for user {}", username, value(EVENT, USER_SERVICE_ERROR));
        return false;
    }

    public boolean userCanAccessOffenderRecord(String oasysId, Long offenderId) {
        return true;
    }

}
