package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.AuthorisationDto;
import uk.gov.justice.digital.oasys.api.OasysUserAuthenticationDto;
import uk.gov.justice.digital.oasys.api.OffenderPermissionResource;
import uk.gov.justice.digital.oasys.api.ValidateUserRequest;
import uk.gov.justice.digital.oasys.service.AuthenticationService;

import static net.logstash.logback.argument.StructuredArguments.value;
import static org.springframework.http.HttpStatus.*;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@RestController
@Api(description = "Authentication resources", tags = "Authentication")
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(path = "/authentication/user/{oasysUserId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OasysUserAuthenticationDto> getUserByUserId(@PathVariable("oasysUserId") String oasysUserId) {

        return authenticationService.getUserByUserId(oasysUserId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/authentication/user/{oasysUserId}/offender/{offenderId}/{resource}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 401, message = "User not authenticated for offender"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<AuthorisationDto> getUserAuthorisedForOffenderId(@PathVariable("oasysUserId") String oasysUserId,
                                                                           @PathVariable("offenderId") Long offenderId,
                                                                           @PathVariable("resource") OffenderPermissionResource resource,
                                                                           @RequestParam("sessionId") Long sessionId) {
        return  ResponseEntity.ok(authenticationService.userCanAccessOffenderRecord(oasysUserId, offenderId, sessionId, resource));
    }

    @RequestMapping(path = "/authentication/user/validate", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 401, message = "Incorrect Credentials"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity validateUser(@RequestBody ValidateUserRequest request) {
        boolean authorised = authenticationService.validateUserCredentials(request.getUser(), request.getPassword());
        log.info("User {} Authenticated {}", request.getUser(), authorised, value(EVENT, USER_AUTHENTICATION));
        return authorised ? new ResponseEntity(OK) : new ResponseEntity(UNAUTHORIZED);
    }

}
