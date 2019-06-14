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
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.api.SentencePlanNeeds;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.SentencePlanService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Sentence Plan resources", tags = "Offender Sentence Plans")
public class SentencePlansController {

    private final SentencePlanService sentencePlanService;

    @Autowired
    public SentencePlansController(SentencePlanService sentencePlanService) {
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getLatestBasicSentencePlanForOffenderPk(oasysOffenderId, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getLatestBasicSentencePlanForOffenderCrn(crn, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getLatestBasicSentencePlanForOffenderPnc(pnc, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getLatestBasicSentencePlanForOffenderNomsId(nomisId, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getLatestBasicSentencePlanForOffenderBookingId(bookingId, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getBasicSentencePlansForOffenderPk(oasysOffenderId, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getBasicSentencePlansForOffenderCrn(crn, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getBasicSentencePlansForOffenderPnc(pnc, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getBasicSentencePlansForOffenderNomsId(nomisId, assessmentsFilter)
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
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getBasicSentencePlansForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderPk(@PathVariable("oasysOffenderId") Long oasysOffenderId) {

        return sentencePlanService.createBasicSentencePlanForOffenderPk(oasysOffenderId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderCrn(@PathVariable("crn") String crn) {

        return sentencePlanService.createBasicSentencePlanForOffenderCrn(crn)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderPnc(@PathVariable("pnc") String pnc) {

        return sentencePlanService.createBasicSentencePlanForOffenderPnc(pnc)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderNomisId(@PathVariable("nomisId") String nomisId) {

        return sentencePlanService.createBasicSentencePlanForOffenderNomisId(nomisId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/sentencePlans", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> createSentencePlanForOffenderBookingId(@PathVariable("bookingId") String bookingId) {

        return sentencePlanService.createBasicSentencePlanForOffenderBookingId(bookingId)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/sentencePlans/{sentencePlanId}/sentencePlanItems/{spratSpCode}", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlanItem> addSentencePlanItem(@PathVariable("sentencePlanId") Long sentencePlanId,
                                                                     @PathVariable("spratSpCode") SentencePlanNeeds spratSpCode,
                                                                     @RequestBody BasicSentencePlanItem sentencePlanItem) {

        return sentencePlanService.addBasicSentencePlanItem(sentencePlanId, spratSpCode, sentencePlanItem)
                .map(sp -> new ResponseEntity<>(sp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/oasysOffenderId/{oasysOffenderId}/properSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getProperSentencePlansForOffenderPk(@PathVariable("oasysOffenderId") Long oasysOffenderId,
                                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getProperSentencePlansForOffenderPk(oasysOffenderId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/crn/{crn}/properSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getProperSentencePlansForOffenderCrn(@PathVariable("crn") String crn,
                                                                                  @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                  @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                  @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                  @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getProperSentencePlansForOffenderCrn(crn, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/pnc/{pnc}/properSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getProperSentencePlansForOffenderPnc(@PathVariable("pnc") String pnc,
                                                                                  @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                  @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                  @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                  @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getProperSentencePlansForOffenderPnc(pnc, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomisId/{nomisId}/properSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getProperSentencePlansForOffenderNomisId(@PathVariable("nomisId") String nomisId,
                                                                                      @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                      @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                      @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                      @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getProperSentencePlansForOffenderNomsId(nomisId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/bookingId/{bookingId}/properSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getProperSentencePlansForOffenderBookingId(@PathVariable("bookingId") String bookingId,
                                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                sentencePlanService.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return sentencePlanService.getProperSentencePlansForOffenderBookingId(bookingId, assessmentsFilter)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

}
