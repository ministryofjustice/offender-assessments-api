package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

import static uk.gov.justice.digital.oasys.api.DtoUtils.refElementDesc;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderSummaryDto {
    @Getter
    @JsonProperty("oasysOffenderId")
    private Long oasysOffenderId;
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
    @JsonProperty("identifiers")
    private IdentifiersDto identifiers;

    public static OffenderSummaryDto from(Offender offender) {
        IdentifiersDto identifiers = IdentifiersDto.from(offender);
        return new OffenderSummaryDto(
                offender.getOffenderPk(),
                offender.getFamilyName(),
                offender.getForename1(),
                offender.getForename2(),
                offender.getForename3(),
                refElementDesc(offender.getRiskToOthers()),
                identifiers);
    }
}