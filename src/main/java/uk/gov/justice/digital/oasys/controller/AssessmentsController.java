package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.simple.AssessmentDto;
import uk.gov.justice.digital.oasys.api.simple.AssessmentSummaryDto;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.Collection;

@RestController
@Api(tags = "Offender Assessments")
@Slf4j
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @Autowired
    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
    }

    @GetMapping(path = "/assessments/oasysSetId/{oasysSetId}")
    @ApiOperation(value = "Gets an assessment by its identity")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Assessment or Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<AssessmentDto> getAssessment(@PathVariable("oasysSetId") Long oasysSetId) {
        log.info("Fetching Assessment for oasysSetId: {}", oasysSetId, LogEvent.GET_ASSESSMENT);
        var assessmentDto = assessmentsService.getAssessment(oasysSetId);
        log.info("Found Assessment type: {} status: {} for oasysSetId: {}", assessmentDto.getAssessmentType(), assessmentDto.getAssessmentStatus(), oasysSetId, LogEvent.GET_ASSESSMENT_FOUND);
        return ResponseEntity.ok(assessmentDto);
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}/assessments/summary")
    @ApiOperation(value = "Gets all assessments for an offender")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Collection<AssessmentSummaryDto>> getAssessmentsForOffender(@PathVariable("identityType") String identityType,
                                                                            @PathVariable("identity") String identity,
                                                                            @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                            @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                            @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                            @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        log.info("Fetching Assessment summary for identity: {},{}", identityType, identity, LogEvent.GET_ASSESSMENT_SUMMARY);
        var assessmentSummaryDto = assessmentsService.getAssessmentsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found Assessment summaries for identity: {},{}", identityType, identity, LogEvent.GET_ASSESSMENT_SUMMARY_FOUND);
        return ResponseEntity.ok(assessmentSummaryDto);
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}/assessments/latest")
    @ApiOperation(value = "Gets the latest assessment for an offender")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Assessment or Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<AssessmentDto> getAssessmentsForOffenderPkLatest(@PathVariable("identityType") String identityType,
                                                                           @PathVariable("identity") String identity,
                                                                           @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                           @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                           @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                           @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        log.info("Fetching latest Assessment for identity: {},{}", identityType, identity, LogEvent.GET_LATEST_ASSESSMENT);
        var latestAssessment = assessmentsService.getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found latest Assessment type: {} status: {} for identity: {},{}", latestAssessment.getAssessmentType(), latestAssessment.getAssessmentStatus(), identityType, identity, LogEvent.GET_LATEST_ASSESSMENT_FOUND);
        return ResponseEntity.ok(latestAssessment);
    }

}