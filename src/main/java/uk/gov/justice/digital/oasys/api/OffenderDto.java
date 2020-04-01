package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.simple.OffenderSummary;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderDto {
    @JsonProperty("oasysOffenderId")
    private Long oasysOffenderId;
    @JsonProperty("limitedAccessOffender")
    private boolean limitedAccessOffender;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("forename1")
    private String forename1;
    @JsonProperty("forename2")
    private String forename2;
    @JsonProperty("forename3")
    private String forename3;
    @JsonProperty("riskToOthers")
    private String riskToOthers;
    @JsonProperty("riskToSelf")
    private String riskToSelf;
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

    public static OffenderDto from(OffenderSummary offender) {
        if (offender == null) {
            return null;
        }
        return new OffenderDto(
                offender.getOffenderPk(),
                DtoUtils.ynToBoolean(offender.getLimitedAccessOffender()),
                offender.getFamilyName(),
                offender.getForename1(),
                offender.getForename2(),
                offender.getForename3(),
                offender.getRiskToOthers(),
                offender.getRiskToSelf(),
                offender.getPnc(),
                offender.getCmsProbNumber(),
                offender.getCmsPrisNumber(),
                offender.getLegacyCmsProbNumber(),
                offender.getCroNumber(),
                offender.getPrisonNumber(),
                offender.getMergePncNumber());
    }
}