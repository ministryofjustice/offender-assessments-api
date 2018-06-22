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
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.api.Ovp;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.service.OgpService;
import uk.gov.justice.digital.oasys.service.OgrsService;
import uk.gov.justice.digital.oasys.service.OvpService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender ASSESSMENTS")
public class AssessmentsController {

    private final OgrsService ogrsService;
    private final OgpService ogpService;
    private final OvpService ovpService;

    @Autowired
    public AssessmentsController(OgrsService ogrsService, OgpService ogpService, OvpService ovpService, AssessmentsService assessmentsService) {
        this.ogrsService = ogrsService;
        this.ogpService = ogpService;
        this.ovpService = ovpService;
        this.assessmentService = assessmentsService
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

    @RequestMapping(path = "/offenders/crn/{crn}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderCrn(@PathVariable("crn") String crn) {

        return ogpService.getOgpForOffenderCRN(crn)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/crn/{crn}/assessments/{assessmentsType}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Assessments>> getAssessmentsTypesForOffenderCrn(@PathVariable("crn") String crn, @PathVariable("assessmentsType") String assessmentType) {

        return assessmentsService.getAssessmentsTypesForOffenderCRN(crn,assessmentsType)
                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/assessment/{assessmentType}/latest}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Assessments>> getLatestAssessmentForOffenderCrn(@PathVariable("crn") String crn, @PathVariable("assessmentType") String assessmentType) {

        return assessmentsService.getLatestAssessmentForOffenderCRN(crn,assessmentType)
                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Assessments>> getAssessmentsForOffenderCrn(@PathVariable("crn") String crn) {

        return assessmentsService.getAssessmentForOffenderCRN(crn)
                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }



}
