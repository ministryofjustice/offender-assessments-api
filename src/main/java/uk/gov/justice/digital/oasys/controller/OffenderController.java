package uk.gov.justice.digital.oasys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.oasys.api.simple.OffenderDto;
import uk.gov.justice.digital.oasys.service.OffenderService;
import uk.gov.justice.digital.oasys.utils.LogEvent;

@RestController
@Api(tags = "Offenders")
@Slf4j
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @GetMapping(path = "/offenders/{identityType}/{identity}")
    @ApiOperation(value = "Gets an offender by its identity")
    public ResponseEntity<OffenderDto> getOffenderByPk(@PathVariable("identityType") String identityType, @PathVariable("identity") String identity) {
        return ResponseEntity.ok(offenderService.getOffender(identityType, identity));
    }

}