package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderSummaryDto {
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
                identifiers);
    }
}