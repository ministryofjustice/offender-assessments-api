package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.api.OffenderSummaryDto;
import uk.gov.justice.digital.oasys.service.OffenderService;

@RestController
@Api(description = "Offender resources", tags = "Offenders")
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
    public ResponseEntity<Offender> getOffenderByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(Offender.from(offenderService.findOffender(identityType, identity)));
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/summary", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OffenderSummaryDto> getOffenderSummaryByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(OffenderSummaryDto.from(offenderService.findOffender(identityType, identity)));
    }

}