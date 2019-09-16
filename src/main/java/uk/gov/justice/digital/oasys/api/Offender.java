package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Value
@Builder(toBuilder = true)
public class Offender {
    private Long oasysOffenderId;
    private String title;
    private String familyName;
    private String forename1;
    private String forename2;
    private String forename3;
    private Identifiers identifiers;
    private LocalDate dateOfBirth;
    private Boolean deceased;
    private LocalDate dateOfDeath;
    private String limitedAccessOffender;
    private Address address;
    private LocalDate dateOfDeportation;
    private Boolean offenderManaged;
    private Boolean ppo;
    private Boolean remand;
    private String ethnicCategory;
    private String gender;
    private String riskToOthers;
    private String riskToSelf;
    private String offenderHistoric;
    private Boolean nfa;
    private Boolean custody;
    private Boolean merged;
    private Long cmsEventNumber;
    private Boolean lifer;
    private String dischargeCode;
    private List<OffenderAlias> aliases;
    private Set<Sentence> sentence;
}
