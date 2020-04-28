package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.Predictor;
import uk.gov.justice.digital.oasys.service.PredictionService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.Collection;

@RestController
@Api(value = "Offender OGP, OGRs and OVP score resources", tags = "Offender OGP, OGRs, OVP Predictors")
@Slf4j
public class PredictorsController {

    private final PredictionService predictionService;

    @Autowired
    public PredictorsController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }


    @RequestMapping(path = "/offenders/{identityType}/{identity}/predictors", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Collection<Predictor>> getPredictorScoresForOasysOffenderId(@PathVariable("identityType") String identityType,
                                                                               @PathVariable("identity") String identity) {
        log.info("Fetching Assessment OGPs for identity: {},{}", identityType, identity, LogEvent.GET_OFFENDER_PREDICTORS);
        var predictors =  predictionService.getAllPredictorsForOffender(identityType, identity);
        log.info("Found Assessment OGPs for identity: {},{}", identityType, identity, LogEvent.GET_OFFENDER_PREDICTORS_FOUND);
        return ResponseEntity.ok(predictors);
    }
}
