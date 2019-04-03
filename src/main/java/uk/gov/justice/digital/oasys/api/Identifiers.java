package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Identifiers {
    private String pnc;
    private String crn;
    private String nomisId;
    private String legacyCmsProbNumber;
    private String croNumber;
    private String bookingNumber;
    private String mergePncNumber;
}
