package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.api.ValidateUserRequest;
import uk.gov.justice.digital.oasys.service.AuthenticationService;

import static org.springframework.http.HttpStatus.*;

@RestController
@Api(description = "Authentication resources", tags = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(path = "/authentication/user/{oasysUserId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OasysUserAuthentication> getUserByUserId(@PathVariable("oasysUserId") String oasysUserId) {

        return authenticationService.getUserByUserId(oasysUserId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/authentication/user/{oasysUserId}/offender/{offenderId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 401, message = "User not authenticated for offender"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity getUserAuthorisedForOffenderId(@PathVariable("oasysUserId") String oasysUserId, @PathVariable("offenderId") Long offenderId) {
        boolean authorised = authenticationService.getUserAuthentication(oasysUserId, offenderId);
        return authorised ? new ResponseEntity(OK) : new ResponseEntity(UNAUTHORIZED);
    }

    @RequestMapping(path = "/authentication/user/validate", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 401, message = "Incorrect Credentials"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity validateUser(@RequestBody ValidateUserRequest request) {
        boolean authorised = authenticationService.validateUser(request.getUser(), request.getPassword());
        return authorised ? new ResponseEntity(OK) : new ResponseEntity(UNAUTHORIZED);
    }



}
