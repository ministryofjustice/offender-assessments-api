package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class OffenderAlias {
    private Long offenderAliasPk;
    private LocalDate dateOfBirth;
    private String familyName;
    private String forename1;
    private String forename2;
}
