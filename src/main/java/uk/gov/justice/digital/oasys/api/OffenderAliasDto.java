package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderAlias;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OffenderAliasDto {

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

    public static List<OffenderAliasDto> from(List<OffenderAlias> aliases) {
        return aliases.stream().map(OffenderAliasDto::from).collect(Collectors.toList());
    }

    private static OffenderAliasDto from(OffenderAlias alias) {
        return new OffenderAliasDto(
                alias.getOffenderAliasPk(),
                alias.getAliasDateOfBirth(),
                alias.getAliasFamilyName(),
                alias.getAliasForename1(),
                alias.getAliasForename2());
    }

}