package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.OffenderDto;
import uk.gov.justice.digital.oasys.api.OffenderSummaryDto;
import uk.gov.justice.digital.oasys.service.OffenderService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

@RestController
@Api(description = "Offender resources", tags = "Offenders")
@Slf4j
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OffenderDto> getOffenderByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(offenderService.findOffender(identityType, identity));
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/summary", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OffenderSummaryDto> getOffenderSummaryByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        log.info("Retrieving Offender summary for Identity {},{}", identityType, identity, LogEvent.GET_OFFENDER_SUMMARY);
        var offender = offenderService.findOffenderSummary(identityType, identity);
        log.info("Found Offender summary {} for Identity {},{}", offender.getOasysOffenderId(), identityType, identity, LogEvent.GET_OFFENDER_SUMMARY_FOUND);
        return ResponseEntity.ok(offender);
    }

}