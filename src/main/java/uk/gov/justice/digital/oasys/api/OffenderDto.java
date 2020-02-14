package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static uk.gov.justice.digital.oasys.api.DtoUtils.refElementDesc;
import static uk.gov.justice.digital.oasys.api.DtoUtils.ynToBoolean;

@Value
@Builder(access = AccessLevel.PRIVATE)
public class OffenderDto {
    @JsonProperty("oasysOffenderId")
    private Long oasysOffenderId;
    @JsonProperty("title")
    private String title;
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
    @JsonProperty("dateOfBirth")
    private LocalDate dateOfBirth;
    @JsonProperty("deceased")
    private Boolean deceased;
    @JsonProperty("dateOfDeath")
    private LocalDate dateOfDeath;
    @JsonProperty("limitedAccessOffender")
    private String limitedAccessOffender;
    @JsonProperty("address")
    private AddressDto address;
    @JsonProperty("dateOfDeportation")
    private LocalDate dateOfDeportation;
    @JsonProperty("offenderManaged")
    private Boolean offenderManaged;
    @JsonProperty("ppo")
    private Boolean ppo;
    @JsonProperty("remand")
    private Boolean remand;
    @JsonProperty("ethnicCategory")
    private String ethnicCategory;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("riskToOthers")
    private String riskToOthers;
    @JsonProperty("riskToSelf")
    private String riskToSelf;
    @JsonProperty("offenderHistoric")
    private String offenderHistoric;
    @JsonProperty("nfa")
    private Boolean nfa;
    @JsonProperty("custody")
    private Boolean custody;
    @JsonProperty("merged")
    private Boolean merged;
    @JsonProperty("cmsEventNumber")
    private Long cmsEventNumber;
    @JsonProperty("lifer")
    private Boolean lifer;
    @JsonProperty("dischargeCode")
    private String dischargeCode;
    @JsonProperty("aliases")
    private List<OffenderAliasDto> aliases;
    @JsonProperty("sentence")
    private Set<SentenceDto> sentence;

    public static OffenderDto from(Offender offender) {

        return OffenderDto.builder()
                .address(AddressDto.from(offender))
                .aliases(OffenderAliasDto.from(offender.getOffenderAliases()))
                .cmsEventNumber(offender.getCmsEventNumber())
                .custody(ynToBoolean(offender.getCustodyInd()))
                .dateOfBirth(offender.getDateOfBirth())
                .dateOfDeath(offender.getDateOfDeath())
                .dateOfDeportation(offender.getDateOfDeportation())
                .deceased(ynToBoolean(offender.getDeceasedInd()))
                .dischargeCode(refElementDesc(offender.getDischargeCode()))
                .ethnicCategory(refElementDesc(offender.getEthnicCategory()))
                .familyName(offender.getFamilyName())
                .forename1(offender.getForename1())
                .forename2(offender.getForename2())
                .forename3(offender.getForename3())
                .gender(refElementDesc(offender.getGender()))
                .identifiers(IdentifiersDto.from(offender))
                .lifer(ynToBoolean(offender.getLifeInd()))
                .limitedAccessOffender(offender.getLimitedAccessOffender())
                .merged(ynToBoolean(offender.getMergedInd()))
                .nfa(ynToBoolean(offender.getNfaInd()))
                .offenderHistoric(refElementDesc(offender.getOffenderHistoric()))
                .offenderManaged(ynToBoolean(offender.getOffenderManagedInd()))
                .oasysOffenderId(offender.getOffenderPk())
                .ppo(ynToBoolean(offender.getPpoInd()))
                .remand(ynToBoolean(offender.getRemandInd()))
                .riskToOthers(refElementDesc(offender.getRiskToOthers()))
                .riskToSelf(refElementDesc(offender.getRiskToSelf()))
                .sentence(SentenceDto.from(offender.getOasysAssessmentGroups()))
                .build();
    }
}