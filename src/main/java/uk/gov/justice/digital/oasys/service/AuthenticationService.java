package uk.gov.justice.digital.oasys.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.applicationinsights.TelemetryClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.AuthorisationDto;
import uk.gov.justice.digital.oasys.api.OasysUserAuthenticationDto;
import uk.gov.justice.digital.oasys.api.OffenderPermissionLevel;
import uk.gov.justice.digital.oasys.api.OffenderPermissionResource;
import uk.gov.justice.digital.oasys.jpa.entity.AuthenticationStatus;
import uk.gov.justice.digital.oasys.jpa.entity.AuthorisationStatus;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAuthenticationRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS;
import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.api.OffenderPermissionLevel.*;
import static uk.gov.justice.digital.oasys.api.OffenderPermissionResource.SENTENCE_PLAN;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Service
@Slf4j
public class AuthenticationService {

    private OasysUserRepository oasysUserRepository;
    private OasysAuthenticationRepository oasysAuthenticationRepository;
    private ObjectMapper objectMapper;
    private TelemetryClient telemetryClient;


    @Autowired
    public AuthenticationService(OasysUserRepository oasysUserRepository, OasysAuthenticationRepository oasysAuthenticationRepository, TelemetryClient telemetryClient) {
        this.oasysUserRepository = oasysUserRepository;
        this.oasysAuthenticationRepository = oasysAuthenticationRepository;
        this.telemetryClient = telemetryClient;
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.enable(UNWRAP_SINGLE_VALUE_ARRAYS);
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Cacheable("users")
    public Optional<OasysUserAuthenticationDto> getUserByUserId(String username) {
        log.info("Retrieving user with OASys username {}", username, value(EVENT, USER_AUTHENTICATION));
        return oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(username).map(user -> OasysUserAuthenticationDto.from(user));
    }

    public boolean validateUserCredentials(String username, String password) {
        log.info("Attempting to authenticate user {}", username, value(EVENT, USER_AUTHENTICATION_ATTEMPT));
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            logAuthResult(Objects.isNull(username) ? "" : username, false);
            return false;
        }

        Optional<String> response = oasysAuthenticationRepository.validateCredentials(username, password);

        if (response.isPresent()) {
            try {
                var result = objectMapper.readValue(response.get(), AuthenticationStatus.class).isAuthenticated();
                logAuthResult(username, result);
                return result;
            } catch (IOException e) {
                logAuthResult(username, false);
                log.error("Failed to parse OASys response for user {} response: {}", username, response.get(), value(EVENT, USER_AUTHENTICATION_PARSE_ERROR));
                return false;
            }
        }

        log.info("No response from OASus authentication function for user {}", username, value(EVENT, USER_SERVICE_ERROR));
        return false;
    }

    public AuthorisationDto userCanAccessOffenderRecord(String oasysUserCode, long offenderId, Optional<Long> sessionId, OffenderPermissionResource resource) {
        log.info("Attempting to authorise user user {} for offender {}", oasysUserCode, offenderId, value(EVENT, USER_AUTHENTICATION_ATTEMPT));

        if(StringUtils.isEmpty(oasysUserCode)) {
            return new AuthorisationDto(UNAUTHORISED);
        }

        if(sessionId.isEmpty()) {
           sessionId = oasysUserRepository.findCurrentUserSessionForOffender(offenderId, oasysUserCode);
        }

        Optional<String> response = authoriseSentencePlan(oasysUserCode, offenderId, sessionId.orElse(0l));

        if (response.isPresent()) {
            try {
                var authStatus = objectMapper.readValue(response.get(), AuthorisationStatus.class);

                switch (authStatus.getState()) {
                    case READ:
                        logAuthSuccess(oasysUserCode, READ_ONLY, SENTENCE_PLAN, offenderId);
                        return new AuthorisationDto(oasysUserCode, offenderId, READ_ONLY, SENTENCE_PLAN);
                    case EDIT:
                        logAuthSuccess(oasysUserCode, WRITE, SENTENCE_PLAN, offenderId);
                        return new AuthorisationDto(oasysUserCode, offenderId, WRITE, SENTENCE_PLAN);
                    case NO_ACCESS:
                        logAuthFailure(oasysUserCode, "Unauthorised", offenderId);
                        return new AuthorisationDto(oasysUserCode, offenderId, UNAUTHORISED, SENTENCE_PLAN);
                    default:
                        logAuthFailure(oasysUserCode, "Invalid OASys Response", offenderId);
                        log.error("Failed to authorise user {} for offender {} with status {}", oasysUserCode, offenderId, authStatus.getState(), value(EVENT, USER_AUTHENTICATION_PARSE_ERROR));
                        return new AuthorisationDto(oasysUserCode, offenderId, UNAUTHORISED, SENTENCE_PLAN);
                }

            } catch (IOException e) {
                logAuthFailure(oasysUserCode, "Parse Error", offenderId);
                log.error("Failed to parse OASys response for user {} response: {}", oasysUserCode, response.get(), value(EVENT, USER_AUTHENTICATION_PARSE_ERROR));
                return new AuthorisationDto(UNAUTHORISED);
            }
        }
        logAuthFailure(oasysUserCode, "No response from OASys", offenderId);
        return new AuthorisationDto(oasysUserCode, offenderId, UNAUTHORISED, SENTENCE_PLAN);
    }

    private Optional<String> authoriseSentencePlan(String oasysUserId, Long offenderId, long sessionId) {
        return oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession(oasysUserId, offenderId, sessionId);
    }

    private void logAuthResult(String username, boolean success) {
        var event = success ? "OASysAuthenticationSuccess" : "OASysAuthenticationFailure";
        telemetryClient.trackEvent(event, Map.of("username", username), null);
    }


    private void logAuthSuccess(String username, OffenderPermissionLevel permissionLevel, OffenderPermissionResource resource, Long offenderId) {
        telemetryClient.trackEvent("OASysAuthorisationSuccess", Map.of("username", username,
                "accessGranted", permissionLevel.toString(),
                "resource", resource.toString(),
                "offender", offenderId.toString()), null);
    }

    private void logAuthFailure(String username, String reason, Long offenderId) {
        telemetryClient.trackEvent("OASysAuthorisationFailure", Map.of("username", username,
                "reason", reason,
                "offender", offenderId.toString()), null);
    }

}
