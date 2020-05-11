package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.OffenderDto;
import uk.gov.justice.digital.oasys.service.OffenderService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

@RestController
@Api(value= "Offender ", tags = "Offenders")
@Slf4j
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}")
    @ApiOperation(value = "Gets an offender by its identity")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OffenderDto> getOffenderByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        log.info("Fetching offender for identity: {},{}", identityType, identity, LogEvent.GET_OFFENDER_SUMMARY);
        var offender = offenderService.getOffender(identityType, identity);
        log.info("Found offender for identity: {},{}", identityType, identity, LogEvent.GET_OFFENDER_SUMMARY_FOUND);
        return ResponseEntity.ok(offender);
    }

}