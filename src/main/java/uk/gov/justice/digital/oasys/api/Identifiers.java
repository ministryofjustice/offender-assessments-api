package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;

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

    public static Identifiers from(OffenderEntity offenderEntity) {
        return new Identifiers(
                offenderEntity.getPnc(),
                offenderEntity.getCmsProbNumber(),
                offenderEntity.getCmsPrisNumber(),
                offenderEntity.getLegacyCmsProbNumber(),
                offenderEntity.getCroNumber(),
                offenderEntity.getPrisonNumber(),
                offenderEntity.getMergePncNumber());
    }
}