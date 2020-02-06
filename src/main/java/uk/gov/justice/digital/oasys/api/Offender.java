package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static uk.gov.justice.digital.oasys.transformer.OffenderTransformer.sentenceOf;
import static uk.gov.justice.digital.oasys.transformer.TypesTransformer.localDateOf;
import static uk.gov.justice.digital.oasys.transformer.TypesTransformer.ynToBoolean;

@Value
@Builder(access = AccessLevel.PRIVATE)
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

    public static Offender from(OffenderEntity offenderEntity) {
        return Offender.builder()
                .address(Address.from(offenderEntity))
                .aliases(offenderEntity.getOffenderAliases() == null ? null : OffenderAlias.from(offenderEntity.getOffenderAliases()))
                .cmsEventNumber(offenderEntity.getCmsEventNumber())
                .custody(ynToBoolean(offenderEntity.getCustodyInd()))
                .dateOfBirth(localDateOf(offenderEntity.getDateOfBirth()))
                .dateOfDeath(localDateOf(offenderEntity.getDateOfDeath()))
                .dateOfDeportation(localDateOf(offenderEntity.getDateOfDeportation()))
                .deceased(ynToBoolean(offenderEntity.getDeceasedInd()))
                .dischargeCode(Optional.ofNullable(offenderEntity.getDischargeCode()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .ethnicCategory(Optional.ofNullable(offenderEntity.getEthnicCategory()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .familyName(offenderEntity.getFamilyName())
                .forename1(offenderEntity.getForename1())
                .forename2(offenderEntity.getForename2())
                .forename3(offenderEntity.getForename3())
                .gender(Optional.ofNullable(offenderEntity.getGender()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .identifiers(Identifiers.from(offenderEntity))
                .lifer(ynToBoolean(offenderEntity.getLifeInd()))
                .limitedAccessOffender(offenderEntity.getLimitedAccessOffender())
                .merged(ynToBoolean(offenderEntity.getMergedInd()))
                .nfa(ynToBoolean(offenderEntity.getNfaInd()))
                .offenderHistoric(Optional.ofNullable(offenderEntity.getOffenderHistoric()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .offenderManaged(ynToBoolean(offenderEntity.getOffenderManagedInd()))
                .oasysOffenderId(offenderEntity.getOffenderPk())
                .ppo(ynToBoolean(offenderEntity.getPpoInd()))
                .remand(ynToBoolean(offenderEntity.getRemandInd()))
                .riskToOthers(Optional.ofNullable(offenderEntity.getRiskToOthers()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .riskToSelf(Optional.ofNullable(offenderEntity.getRiskToSelf()).map(RefElement::getRefElementDesc).orElse(null))
                .sentence(sentenceOf(offenderEntity.getOasysAssessmentGroups()))
                .build();
    }
}