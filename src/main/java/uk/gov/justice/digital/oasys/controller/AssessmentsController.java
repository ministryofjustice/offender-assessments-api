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
import uk.gov.justice.digital.oasys.api.AssessmentResource;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.Question;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender Assessments")
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @Autowired
    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
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

    @RequestMapping(path = "/assessments/oasysSetId/{oasysSetId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Assessment not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessment(@PathVariable("oasysSetId") Long oasysSetId) {

        return assessmentsService.getAssessment(oasysSetId).map(
                assessment -> new ResponseEntity<>(assessment, OK)).orElse(new ResponseEntity<>(NOT_FOUND));

    }


    @RequestMapping(path = "/offenders/crn/{crn}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentResource>> getAssessmentsForOffenderCrn(@PathVariable("crn") String crn,
                                                                                 @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                 @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                 @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                 @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        final Optional<List<AssessmentResource>> maybeAssessmentsForOffenderCRN = assessmentsService.getAssessmentsForOffenderCRN(crn, assessmentsFilter);

        return maybeAssessmentsForOffenderCRN.map(assessments -> new ResponseEntity<>(assessments, OK)).orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentResource>> getAssessmentsForOffenderPnc(@PathVariable("pnc") String pnc,
                                                                                 @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                 @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                 @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                 @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        final Optional<List<AssessmentResource>> maybeAssessmentsForOffenderPnc = assessmentsService.getAssessmentsForOffenderPnc(pnc, assessmentsFilter);

        return maybeAssessmentsForOffenderPnc.map(assessments -> new ResponseEntity<>(assessments, OK)).orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentResource>> getAssessmentsForOffenderNomisId(@PathVariable("nomisId") String nomisId,
                                                                                     @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                     @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                     @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                     @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        final Optional<List<AssessmentResource>> maybeAssessmentsForOffenderNomisId = assessmentsService.getAssessmentsForOffenderNomisId(nomisId, assessmentsFilter);

        return maybeAssessmentsForOffenderNomisId.map(assessments -> new ResponseEntity<>(assessments, OK)).orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentResource>> getAssessmentsForOffenderBookingId(@PathVariable("bookingId") String bookingId,
                                                                                       @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                       @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                       @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                       @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        final Optional<List<AssessmentResource>> maybeAssessmentsForOffenderBookingId = assessmentsService.getAssessmentsForOffenderBookingId(bookingId, assessmentsFilter);

        return maybeAssessmentsForOffenderBookingId.map(assessments -> new ResponseEntity<>(assessments, OK)).orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/assessments", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentResource>> getAssessmentsForOffenderBookingId(@PathVariable("oasysOffenderId") Long oasysOffenderId,
                                                                                       @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                       @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                       @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                       @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        final Optional<List<AssessmentResource>> maybeAssessmentsForOffenderOasysId = assessmentsService.getAssessmentsForOffenderPK(oasysOffenderId, assessmentsFilter);

        return maybeAssessmentsForOffenderOasysId.map(assessments -> new ResponseEntity<>(assessments, OK)).orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderPkLatest(@PathVariable("oasysOffenderId") Long oasysOffenderId,
                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestAssessmentForOffenderPk(oasysOffenderId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
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

    @RequestMapping(path = "/offenders/pnc/{pnc}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderPncLatest(@PathVariable("pnc") String pnc,
                                                                         @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                         @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                         @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                         @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestAssessmentForOffenderPnc(pnc, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderNomisIdLatest(@PathVariable("nomisId") String nomisId,
                                                                             @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                             @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                             @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                             @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestAssessmentForOffenderNomisId(nomisId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderBookingIdLatest(@PathVariable("bookingId") String bookingId,
                                                                               @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                               @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                               @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                               @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestAssessmentForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(path = "/offenders/crn/{crn}/assessments/type/{assessmentType}/latest/section/{section}/question/{question:.+}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Question> getQAndAForOffenderCrnLatestAssessmentType(@PathVariable("crn") String crn,
                                                                               @PathVariable("assessmentType") String assessmentType,
                                                                               @PathVariable("section") String section,
                                                                               @PathVariable("question") String question) {
        return assessmentsService.getLatestQAndAforOffenderCRN(crn, assessmentType, section, question)
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

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/sentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestSentencePlanForOffenderPk(@PathVariable("oasysOffenderId") Long oasysOffenderId,
                                                                                @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderPk(oasysOffenderId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/sentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestSentencePlanForOffenderCrn(@PathVariable("crn") String crn,
                                                                                 @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                 @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                 @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                 @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderCrn(crn, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/sentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestSentencePlanForOffenderPnc(@PathVariable("pnc") String pnc,
                                                                                 @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                 @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                 @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                 @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderPnc(pnc, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/sentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestSentencePlanForOffenderNomisId(@PathVariable("nomisId") String nomisId,
                                                                                     @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                     @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                     @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                     @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderNomsId(nomisId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/sentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestSentencePlanForOffenderBookingId(@PathVariable("bookingId") String bookingId,
                                                                                       @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                       @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                       @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                       @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

}
