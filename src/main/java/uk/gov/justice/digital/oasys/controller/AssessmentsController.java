package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.AssessmentDto;
import uk.gov.justice.digital.oasys.api.AssessmentNeed;
import uk.gov.justice.digital.oasys.api.AssessmentSummary;
import uk.gov.justice.digital.oasys.api.QuestionDto;
import uk.gov.justice.digital.oasys.service.AssessmentsService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender Assessments")
@Slf4j
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @Autowired
    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
    }

    @RequestMapping(path = "/assessments/oasysSetId/{oasysSetId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Assessment not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<AssessmentDto> getAssessment(@PathVariable("oasysSetId") Long oasysSetId) {

        log.info("Fetching Assessment for oasysSetId: {}", oasysSetId, LogEvent.GET_ASSESSMENT);
        var assessmentDtoOpt = assessmentsService.getAssessment(oasysSetId);
        if(assessmentDtoOpt.isPresent()) {
            var assessmentDto = assessmentDtoOpt.get();
            log.info("Found Assessment type: {} status: {} for oasysSetId: {}", assessmentDto.getAssessmentType(), assessmentDto.getAssessmentStatus(), oasysSetId, LogEvent.GET_ASSESSMENT_FOUND);
            return ResponseEntity.ok(assessmentDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/summary", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Set<AssessmentSummary>> getAssessmentsForOffender(@PathVariable("identityType") String identityType,
                                                                            @PathVariable("identity") String identity,
                                                                            @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                            @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                            @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                            @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {
        log.info("Fetching Assessment summary for identity: {},{}", identityType, identity, LogEvent.GET_ASSESSMENT_SUMMARY);
        var assessmentSummaryDto = AssessmentSummary.from(assessmentsService.getAssessmentsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
        log.info("Found Assessment summaries for identity: {},{}", identityType, identity, LogEvent.GET_ASSESSMENT_SUMMARY_FOUND);
        return ResponseEntity.ok(assessmentSummaryDto);
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<AssessmentDto> getAssessmentsForOffenderPkLatest(@PathVariable("identityType") String identityType,
                                                                           @PathVariable("identity") String identity,
                                                                           @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                           @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                           @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                           @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {
        log.info("Fetching latest Assessment for identity: {},{}", identityType, identity, LogEvent.GET_LATEST_ASSESSMENT);
        var latestAssessment = assessmentsService.getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found latest Assessment type: {} status: {} for identity: {},{}", latestAssessment.getAssessmentType(), latestAssessment.getAssessmentStatus(), identityType, identity, LogEvent.GET_LATEST_ASSESSMENT_FOUND);
        return ResponseEntity.ok(latestAssessment);
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/type/{assessmentType}/latest/section/{section}/question/{question:.+}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<QuestionDto> getQAndAForOffenderCrnLatestAssessmentType(@PathVariable("identityType") String identityType,
                                                                                  @PathVariable("identity") String identity,
                                                                                  @PathVariable("assessmentType") String assessmentType,
                                                                                  @PathVariable("section") String section,
                                                                                  @PathVariable("question") String question) {
        return ResponseEntity.ok(assessmentsService.getLatestQAndAforOffender(identityType, identity, assessmentType, section, question));
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/latest/needs", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<AssessmentNeed>> getLatestAssessmentNeedsForOffenderPk(@PathVariable("identityType") String identityType,
                                                                                      @PathVariable("identity") String identity,
                                                                                      @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                                      @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                                      @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                                      @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {

        return ResponseEntity.ok(assessmentsService.getLatestAsessementNeedsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }


}