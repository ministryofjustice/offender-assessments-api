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
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.service.OgrsService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Ogrs score resources", tags = "Offender Ogrs")
public class OgrsController {

    private final OgrsService ogrsService;

    @Autowired
    public OgrsController(OgrsService ogrsService) {
        this.ogrsService = ogrsService;
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOasysOffenderId(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return ogrsService.getOgrsForOasysOffenderPk(oasysOffenderId)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderCrn(@PathVariable("crn") String crn) {

        return ogrsService.getOgrsForOffenderCRN(crn)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderPNC(@PathVariable("pnc") String pnc) {

        return ogrsService.getOgrsForOffenderPNC(pnc)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return ogrsService.getOgrsForOffenderNomisId(nomisId)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return ogrsService.getOgrsForOffenderBookingId(bookingId)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
