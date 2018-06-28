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
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.ReferenceAssessment;
import uk.gov.justice.digital.oasys.service.RefAssessmentService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Reference assessment resources", tags = "Reference Assessment")
public class ReferenceAssessmentController {

    public final RefAssessmentService refAssessmentService;

    @Autowired
    public ReferenceAssessmentController(RefAssessmentService refAssessmentService) {
        this.refAssessmentService = refAssessmentService;
    }

    @RequestMapping(path = "/RefAssessment/type/{type}/revision/{revision}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Assessment not found"),
            @ApiResponse(code = 200, message = "OK")})

    public ResponseEntity<ReferenceAssessment> getReferenceAssessmentOf(@PathVariable("type") String type,
                                                                        @PathVariable("revision") String revision) {

        return refAssessmentService.getReferenceAssessmentOf(type, revision)
                .map(referenceAssessment -> new ResponseEntity<>(referenceAssessment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
