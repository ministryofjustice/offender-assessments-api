package uk.gov.justice.digital.oasys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.ReferenceIntervention;
import uk.gov.justice.digital.oasys.service.RefInterventionService;
import java.util.List;


@RestController
@Api(description = "Reference intervention resources", tags = "Interventions")
public class RefInterventionController {

    public final RefInterventionService refInterventionService;

    @Autowired
    public RefInterventionController(RefInterventionService refInterventionService) {
        this.refInterventionService = refInterventionService;
    }

    @RequestMapping(path = "/referenceinterventions", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})

    public ResponseEntity<List<ReferenceIntervention>> getInterventions() {
        return ResponseEntity.ok(refInterventionService.getAllInterventions());
    }
}
