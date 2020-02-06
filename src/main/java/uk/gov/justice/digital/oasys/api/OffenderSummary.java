package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderSummary {
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
    private Identifiers identifiers;

    public static OffenderSummary from(OffenderEntity offenderEntity) {
        Identifiers identifiers = Identifiers.from(offenderEntity);
        return new OffenderSummary(
                offenderEntity.getOffenderPk(),
                offenderEntity.getFamilyName(),
                offenderEntity.getForename1(),
                offenderEntity.getForename2(),
                offenderEntity.getForename3(),
                identifiers);
    }
}