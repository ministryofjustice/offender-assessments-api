package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.api.OffenderSummary;
import uk.gov.justice.digital.oasys.service.OffenderService;

@Api(tags = "Offenders")

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}")
    @ApiOperation(value = "Get an offender by a known identity and type")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(offenderService.getOffender(identityType, identity));
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}/summary")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<OffenderSummary> getOffenderSummaryByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(offenderService.getOffenderSummary(identityType, identity));
    }

}