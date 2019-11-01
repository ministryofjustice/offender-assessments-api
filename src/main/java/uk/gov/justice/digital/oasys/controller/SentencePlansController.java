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
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.SentencePlanService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Sentence Plan resources", tags = "Offender Basic Sentence Plans")
public class SentencePlansController {

    private final SentencePlanService sentencePlanService;

    @Autowired
    public SentencePlansController(SentencePlanService sentencePlanService) {
        this.sentencePlanService = sentencePlanService;
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/basicSentencePlans/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Sentence Plan or Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<BasicSentencePlan> getLatestBasicSentencePlanForOffender(@PathVariable("identityType") String identityType,
                                                                                     @PathVariable("identity") String identity,
                                                                                     @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                     @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                     @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                     @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        return sentencePlanService.getLatestBasicSentencePlanForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)
                .map(assessment -> new ResponseEntity<>(assessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/basicSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<BasicSentencePlan>> getSentenceBasicPlansForOffenderBookingId(@PathVariable("identityType") String identityType,
                                                                                        @PathVariable("identity") String identity,
                                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        return ResponseEntity.ok(sentencePlanService.getBasicSentencePlansForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));

    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/fullSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProperSentencePlan>> getFullSentencePlansForOffender(@PathVariable("identityType") String identityType,
                                                                                         @PathVariable("identity") String identity,
                                                                                         @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                         @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                         @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                         @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        return ResponseEntity.ok(sentencePlanService.getFullSentencePlansForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }

}
