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
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.service.OgrsService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Assessment resources", tags = "Offender ASSESSMENTS")
public class AssessmentsController {

    private final OgrsService ogrsService;

    @Autowired
    public AssessmentsController(OgrsService ogrsService) {
        this.ogrsService = ogrsService;
    }


    @RequestMapping(path = "/offenders/crn/{crn}/ogrs3", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Ogrs>> getOgrsScoreForOffenderCrn(@PathVariable("crn") String crn) {

        return ogrsService.getOgrsForOffenderCRN(crn)
                .map(ogrs -> new ResponseEntity<>(ogrs, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
