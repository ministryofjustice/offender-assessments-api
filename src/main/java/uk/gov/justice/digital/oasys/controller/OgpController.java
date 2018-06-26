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
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.service.OgpService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Ogp score resources", tags = "Offender Ogp")
public class OgpController {

    private final OgpService ogpService;

    @Autowired
    public OgpController(OgpService ogpService) {
        this.ogpService = ogpService;
    }


    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOasysOffenderId(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return ogpService.getOgpForOasysOffenderPk(oasysOffenderId)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderCrn(@PathVariable("crn") String crn) {

        return ogpService.getOgpForOffenderCRN(crn)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderPNC(@PathVariable("pnc") String pnc) {

        return ogpService.getOgpForOffenderPNC(pnc)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return ogpService.getOgpForOffenderNomisId(nomisId)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return ogpService.getOgpForOffenderBookingId(bookingId)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
