package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.transformer.TypesTransformer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderAlias {

    @JsonProperty("offenderAliasPk")
    private Long offenderAliasPk;
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("forename1")
    private String forename1;
    @JsonProperty("forename2")
    private String forename2;

    public static List<OffenderAlias> from(List<uk.gov.justice.digital.oasys.jpa.entity.OffenderAlias> aliases) {
        return aliases.stream().map(OffenderAlias::from).collect(Collectors.toList());
    }

    private static OffenderAlias from(uk.gov.justice.digital.oasys.jpa.entity.OffenderAlias alias) {
        return new OffenderAlias(
                alias.getOffenderAliasPk(),
                TypesTransformer.localDateOf(alias.getAliasDateOfBirth()),
                alias.getAliasFamilyName(),
                alias.getAliasForename1(),
                alias.getAliasForename2());
    }

}