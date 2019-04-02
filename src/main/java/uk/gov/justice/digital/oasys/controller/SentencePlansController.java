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
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.AssessmentsService;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Sentence Plan resources", tags = "Offender Sentence Plans")
public class SentencePlansController {

    private final AssessmentsService assessmentsService;

    @Autowired
    public SentencePlansController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
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

}
