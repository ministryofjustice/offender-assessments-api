package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.Ovp;
import uk.gov.justice.digital.oasys.service.OvpService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Ovp score resources", tags = "Offender Ovp")
public class OvpController {

    private final OvpService ovpService;

    @Autowired
    public OvpController(OvpService ovpService) {
        this.ovpService = ovpService;
    }


    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOasysOffenderId(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return ovpService.getOvpForOasysOffenderPk(oasysOffenderId)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderCrn(@PathVariable("crn") String crn) {

        return ovpService.getOvpForOffenderCRN(crn)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderPNC(@PathVariable("pnc") String pnc) {

        return ovpService.getOvpForOffenderPNC(pnc)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return ovpService.getOvpForOffenderNomisId(nomisId)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return ovpService.getOvpForOffenderBookingId(bookingId)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
