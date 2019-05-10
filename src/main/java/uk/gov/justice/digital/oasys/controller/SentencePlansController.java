package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.BasicSentencePlanItem;
import uk.gov.justice.digital.oasys.api.SpratSpCodes;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.service.SentencePlanService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Sentence Plan resources", tags = "Offender Sentence Plans")
public class SentencePlansController {

    private final AssessmentsService assessmentsService;
    private final SentencePlanService sentencePlanService;

    @Autowired
    public SentencePlansController(AssessmentsService assessmentsService, SentencePlanService sentencePlanService) {
        this.assessmentsService = assessmentsService;
        this.sentencePlanService = sentencePlanService;
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
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

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
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

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
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

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
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

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
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getLatestSentencePlanForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/sentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentencePlansForOffenderPk(@PathVariable("oasysOffenderId") Long oasysOffenderId,
                                                                                 @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                 @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                 @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                 @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getSentencePlansForOffenderPk(oasysOffenderId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/sentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentencePlansForOffenderCrn(@PathVariable("crn") String crn,
                                                                                  @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                  @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                  @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                  @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getSentencePlansForOffenderCrn(crn, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/sentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentencePlansForOffenderPnc(@PathVariable("pnc") String pnc,
                                                                                  @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                  @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                  @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                  @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getSentencePlansForOffenderPnc(pnc, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/sentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentencePlansForOffenderNomisId(@PathVariable("nomisId") String nomisId,
                                                                                      @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                      @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                      @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                      @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getSentencePlansForOffenderNomsId(nomisId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/sentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentencePlansForOffenderBookingId(@PathVariable("bookingId") String bookingId,
                                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return assessmentsService.getSentencePlansForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderPk(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return sentencePlanService.createSentencePlanForOffenderPk(oasysOffenderId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderCrn(@PathVariable("crn") String crn) {

        return sentencePlanService.createSentencePlanForOffenderCrn(crn)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderPnc(@PathVariable("pnc") String pnc) {

        return sentencePlanService.createSentencePlanForOffenderPnc(pnc)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return sentencePlanService.createSentencePlanForOffenderNomisId(nomisId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return sentencePlanService.createSentencePlanForOffenderBookingId(bookingId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/sentencePlans/{sentencePlanId}/sentencePlanItems/{spratSpCode}", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlanItem> addSentencePlanItem(@PathVariable("sentencePlanId") Long sentencePlanId,
                                                                     @PathVariable("spratSpCode") SpratSpCodes spratSpCode,
                                                                     @RequestBody BasicSentencePlanItem sentencePlanItem) {

        return sentencePlanService.addSentencePlanItem(sentencePlanId, spratSpCode, sentencePlanItem)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


}
