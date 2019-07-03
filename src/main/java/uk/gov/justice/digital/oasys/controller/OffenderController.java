package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.service.OffenderService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender resources", tags = "Offenders")
public class OffenderController {

    private final OffenderService offenderService;

    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByPk(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return offenderService.findOffenderByOasysOffenderId(oasysOffenderId)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/bookingNumber/{bookingNumber}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByBookingNumber(@PathVariable("bookingNumber") String bookingNumber) {

        return offenderService.findOffenderByBookingNumber(bookingNumber)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/crn/{crn}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByCrn(@PathVariable("crn") String crn) {

        return offenderService.findOffenderByCrnId(crn)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByNomisId(@PathVariable("nomisId") String nomisId) {

        return offenderService.findOffenderByNomisId(nomisId)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffenderByPnc(@PathVariable("pnc") String pnc) {

        return offenderService.findOffenderBypnc(pnc)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
