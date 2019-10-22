package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.service.AssessmentsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender Assessments")
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
    public ResponseEntity<Assessment> getAssessment(@PathVariable("oasysSetId") Long oasysSetId) {

        return assessmentsService.getAssessment(oasysSetId).map(
                assessment -> new ResponseEntity<>(assessment, OK)).orElse(new ResponseEntity<>(NOT_FOUND));

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
        return ResponseEntity.ok(AssessmentSummary.from(assessmentsService.getAssessmentsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)));
    }

    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/latest", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Assessment> getAssessmentsForOffenderPkLatest(@PathVariable("identityType") String identityType,
                                                                        @PathVariable("identity") String identity,
                                                                        @RequestParam("historicStatus") Optional<String> filterGroupStatus,
                                                                        @RequestParam("assessmentType") Optional<String> filterAssessmentType,
                                                                        @RequestParam("voided") Optional<Boolean> filterVoided,
                                                                        @RequestParam("assessmentStatus") Optional<String> filterAssessmentStatus) {
        return ResponseEntity.ok(assessmentsService.getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/assessments/type/{assessmentType}/latest/section/{section}/question/{question:.+}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Question> getQAndAForOffenderCrnLatestAssessmentType(@PathVariable("identityType") String identityType,
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