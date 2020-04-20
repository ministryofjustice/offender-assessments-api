package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.FullSentencePlanDto;
import uk.gov.justice.digital.oasys.api.SummarySentencePlanDto;
import uk.gov.justice.digital.oasys.service.SentencePlanService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.Collection;


@RestController
@Api(value = "Offender Sentence Plan resources", tags = "Sentence Plans")
@Slf4j
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
                                                                                   @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                                   @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                                   @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                                   @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        return ResponseEntity.ok(sentencePlanService.getLatestBasicSentencePlanForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/basicSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Collection<BasicSentencePlan>> getSentenceBasicPlansForOffender(@PathVariable("identityType") String identityType,
                                                                                          @PathVariable("identity") String identity,
                                                                                          @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                                          @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                                          @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                                          @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        return ResponseEntity.ok(sentencePlanService.getBasicSentencePlansForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));

    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/fullSentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Collection<FullSentencePlanDto>> getFullSentencePlansForOffender(@PathVariable("identityType") String identityType,
                                                                                     @PathVariable("identity") String identity,
                                                                                     @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                                     @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                                     @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                                     @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        log.info("Retrieving Full Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_FULL_SP_LIST);
        var plans = sentencePlanService.getFullSentencePlansForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found Full Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_FULL_SP_LIST_FOUND);
        return ResponseEntity.ok(plans);
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/fullSentencePlans/{oasysSetPk}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<FullSentencePlanDto> getFullSentencePlanForOffender(@PathVariable("identityType") String identityType,
                                                                              @PathVariable("identity") String identity,
                                                                              @PathVariable("oasysSetPk") Long oasysSetPk) {
        log.info("Retrieving Full Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_FULL_SP);
        var plan = sentencePlanService.getFullSentencePlan(oasysSetPk);
        log.info("Found Full Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_FULL_SP_FOUND);
        return ResponseEntity.ok(plan);
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/summarySentencePlans", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Collection<SummarySentencePlanDto>> getSummarySentencePlansForOffender(@PathVariable("identityType") String identityType,
                                                                                     @PathVariable("identity") String identity,
                                                                                     @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                                     @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                                     @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                                     @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        log.info("Retrieving Summary Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_SUMMARY_SP_LIST);
        var plans = sentencePlanService.getSummarySentencePlansForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found Summary Sentence Plans for Identity {},{}", identityType, identity, LogEvent.GET_SUMMARY_SP_LIST_FOUND);
        return ResponseEntity.ok(plans);
    }
}
