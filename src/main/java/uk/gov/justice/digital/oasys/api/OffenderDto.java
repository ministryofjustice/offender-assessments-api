package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Value
@Builder(access = AccessLevel.PRIVATE)
public class OffenderDto {
    private Long oasysOffenderId;
    private String title;
    private String familyName;
    private String forename1;
    private String forename2;
    private String forename3;
    private IdentifiersDto identifiers;
    private LocalDate dateOfBirth;
    private Boolean deceased;
    private LocalDate dateOfDeath;
    private String limitedAccessOffender;
    private AddressDto address;
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
    private List<OffenderAliasDto> aliases;
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

    private static Boolean ynToBoolean(String ynValue) {
        if(ynValue == null) {
            return null;
        }
        return ynValue.equalsIgnoreCase("Y");
    }

    private static String refElementDesc(RefElement refElement) {
        if(refElement == null) {
            return null;
        }
        return refElement.getRefElementDesc();
    }
}