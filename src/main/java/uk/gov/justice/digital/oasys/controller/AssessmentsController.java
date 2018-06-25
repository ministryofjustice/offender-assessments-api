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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.api.Ovp;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.service.OgpService;
import uk.gov.justice.digital.oasys.service.OgrsService;
import uk.gov.justice.digital.oasys.service.OvpService;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender ASSESSMENTS")
public class AssessmentsController {

    private final OgrsService ogrsService;
    private final OgpService ogpService;
    private final OvpService ovpService;
    private final AssessmentsService assessmentsService;

    @Autowired
    public AssessmentsController(OgrsService ogrsService, OgpService ogpService, OvpService ovpService, AssessmentsService assessmentsService) {
        this.ogrsService = ogrsService;
        this.ogpService = ogpService;
        this.ovpService = ovpService;
        this.assessmentsService = assessmentsService;
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

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOasysOffenderId(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return ogpService.getOgpForOasysOffenderPk(oasysOffenderId)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
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

    @RequestMapping(path = "/offenders/pnc/{pnc}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderPNC(@PathVariable("pnc") String pnc) {

        return ogrsService.getOgrsForOffenderPNC(pnc)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/pnc/{pnc}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderPNC(@PathVariable("pnc") String pnc) {

        return ovpService.getOvpForOffenderPNC(pnc)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/ogp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogp>> getOgpScoreForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return ogpService.getOgpForOffenderNomisId(nomisId)
                .map(ogp -> new ResponseEntity<>(ogp, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return ogrsService.getOgrsForOffenderBookingId(bookingId)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/ovp", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ovp>> getOvpScoreForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return ovpService.getOvpForOffenderBookingId(bookingId)
                .map(ovp -> new ResponseEntity<>(ovp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

//    @RequestMapping(path = "/offenders/crn/{crn}/assessments/{assessmentsType}", method = RequestMethod.GET)
//    @ApiResponses({
//            @ApiResponse(code = 404, message = "Offender not found"),
//            @ApiResponse(code = 200, message = "OK")})
//    public ResponseEntity<List<Assessment>> getAssessmentsTypesForOffenderCrn(@PathVariable("crn") String crn, @PathVariable("assessmentsType") String assessmentType) {
//
//        return assessmentsService.getAssessmentsTypesForOffenderCRN(crn, assessmentsType)
//                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(NOT_FOUND));
//    }
//
//    @RequestMapping(path = "/offenders/crn/{crn}/assessment/{assessmentType}/latest}", method = RequestMethod.GET)
//    @ApiResponses({
//            @ApiResponse(code = 404, message = "Offender not found"),
//            @ApiResponse(code = 200, message = "OK")})
//    public ResponseEntity<List<Assessment>> getLatestAssessmentForOffenderCrn(@PathVariable("crn") String crn, @PathVariable("assessmentType") String assessmentType) {
//
//        return assessmentsService.getLatestAssessmentForOffenderCRN(crn,assessmentType)
//                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(NOT_FOUND));
//    }

    @RequestMapping(path = "/offenders/crn/{crn}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Assessment>> getAssessmentsForOffenderCrn(@PathVariable("crn") String crn,
                                                                         @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                         @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                         @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                         @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getAssessmentsForOffenderCRN(crn, assessmentsFilter)
                .map(assessments -> new ResponseEntity<>(assessments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(path = "/offenders/crn/{crn}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderCrnLatest(@PathVariable("crn") String crn,
                                                                         @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                         @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                         @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                         @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestAssessmentForOffenderCRN(crn, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    private Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilterOf(Optional<String> filterAssessmentStatus, Optional<String> filterAssessmentType, Optional<String> filterGroupStatus, Optional<Boolean> filterVoided) {

        return filterAssessmentStatus.map(
                assessmentStatus -> curry(AssessmentFilters.byAssessmentStatus, assessmentStatus))
                .orElse(AssessmentFilters.identity)
                .andThen(
                        filterAssessmentType.map(
                                assessmentType -> curry(AssessmentFilters.byAssessmentType, assessmentType))
                                .orElse(AssessmentFilters.identity))
                .andThen(
                        filterGroupStatus.map(
                                groupStatus -> curry(AssessmentFilters.byGroupStatus, groupStatus))
                                .orElse(AssessmentFilters.identity))
                .andThen(
                        filterVoided.map(
                                voided -> curry(AssessmentFilters.byVoided, voided))
                                .orElse(AssessmentFilters.identity));
    }



}
