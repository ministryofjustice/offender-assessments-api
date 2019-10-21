package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Identifiers {
    @JsonProperty("pnc")
    private String pnc;
    @JsonProperty("crn")
    private String crn;
    @JsonProperty("nomisId")
    private String nomisId;
    @JsonProperty("legacyCmsProbNumber")
    private String legacyCmsProbNumber;
    @JsonProperty("croNumber")
    private String croNumber;
    @JsonProperty("bookingNumber")
    private String bookingNumber;
    @JsonProperty("mergePncNumber")
    private String mergePncNumber;

    public static Identifiers from(Offender offender) {
        return new Identifiers(
                offender.getPnc(),
                offender.getCmsProbNumber(),
                offender.getCmsPrisNumber(),
                offender.getLegacyCmsProbNumber(),
                offender.getCroNumber(),
                offender.getPrisonNumber(),
                offender.getMergePncNumber());
    }
}