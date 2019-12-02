package uk.gov.justice.digital.oasys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.oasys.api.RefElement;
import uk.gov.justice.digital.oasys.service.ReferenceDataService;

import java.util.List;


@RestController
@Api(description = "Reference intervention resources", tags = "Interventions")
public class ReferenceDataController {

    public final ReferenceDataService referenceDataService;

    @Autowired
    public ReferenceDataController(ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    @RequestMapping(path = "/referencedata/{category}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})

    public ResponseEntity<List<RefElement>> getReferenceDataByCategoryCode(@PathVariable("category") String refDataCategoryCode) {
        return ResponseEntity.ok(referenceDataService.getActiveReferenceDataOfCategory(refDataCategoryCode));
    }
}
