package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.simple.AssessmentDto;
import uk.gov.justice.digital.oasys.api.simple.AssessmentSummaryDto;
import uk.gov.justice.digital.oasys.service.AssessmentsService;

import java.util.Collection;

@RestController
@Api(tags = "Offender Assessments")
public class AssessmentsController {

    private final AssessmentsService assessmentsService;

    @Autowired
    public AssessmentsController(AssessmentsService assessmentsService) {
        this.assessmentsService = assessmentsService;
    }

    @GetMapping(path = "/assessments/oasysSetId/{oasysSetId}")
    @ApiOperation(value = "Gets an assessment by its identity")
    public ResponseEntity<AssessmentDto> getAssessment(@PathVariable("oasysSetId") Long oasysSetId) {
        return ResponseEntity.ok(assessmentsService.getAssessment(oasysSetId));
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}/assessments/summary")
    @ApiOperation(value = "Gets all assessments for an offender")
    public ResponseEntity<Collection<AssessmentSummaryDto>> getAssessmentsForOffender(@PathVariable("identityType") String identityType,
                                                                                      @PathVariable("identity") String identity,
                                                                                      @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                                      @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                                      @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                                      @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        return ResponseEntity.ok(assessmentsService.getAssessmentsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}/assessments/latest")
    @ApiOperation(value = "Gets the latest assessment for an offender")
    public ResponseEntity<AssessmentDto> getAssessmentsForOffenderPkLatest(@PathVariable("identityType") String identityType,
                                                                           @PathVariable("identity") String identity,
                                                                           @RequestParam(value = "historicStatus", required = false) String filterGroupStatus,
                                                                           @RequestParam(value = "assessmentType", required = false) String filterAssessmentType,
                                                                           @RequestParam(value = "voided", required = false) Boolean filterVoided,
                                                                           @RequestParam(value = "assessmentStatus", required = false) String filterAssessmentStatus) {
        return ResponseEntity.ok(assessmentsService.getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus));
    }

}