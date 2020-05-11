package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderSummary;

import java.util.Objects;

@Value
public class OffenderDto {
    Long oasysOffenderId;
    boolean limitedAccessOffender;
    String familyName;
    String forename1;
    String forename2;
    String forename3;
    String riskToOthers;
    String riskToSelf;
    String pnc;
    String crn;
    String nomisId;
    String legacyCmsProbNumber;
    String croNumber;
    String bookingNumber;
    String mergePncNumber;
    Long mergedOasysOffenderId;

    public static OffenderDto from(OffenderSummary offender) {
        if (Objects.isNull(offender)) {
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
                offender.getMergePncNumber(),
                offender.getMergedOffenderPK());
    }
}