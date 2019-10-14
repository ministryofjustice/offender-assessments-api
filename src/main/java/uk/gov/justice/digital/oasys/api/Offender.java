package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
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

    public static Offender from(uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return Offender.builder()
                .address(Address.from(offender))
                .aliases(offender.getOffenderAliases() == null ? null : OffenderAlias.from(offender.getOffenderAliases()))
                .cmsEventNumber(offender.getCmsEventNumber())
                .custody(ynToBoolean(offender.getCustodyInd()))
                .dateOfBirth(localDateOf(offender.getDateOfBirth()))
                .dateOfDeath(localDateOf(offender.getDateOfDeath()))
                .dateOfDeportation(localDateOf(offender.getDateOfDeportation()))
                .deceased(ynToBoolean(offender.getDeceasedInd()))
                .dischargeCode(Optional.ofNullable(offender.getDischargeCode()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .ethnicCategory(Optional.ofNullable(offender.getEthnicCategory()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .familyName(offender.getFamilyName())
                .forename1(offender.getForename1())
                .forename2(offender.getForename2())
                .forename3(offender.getForename3())
                .gender(Optional.ofNullable(offender.getGender()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .identifiers(Identifiers.from(offender))
                .lifer(ynToBoolean(offender.getLifeInd()))
                .limitedAccessOffender(offender.getLimitedAccessOffender())
                .merged(ynToBoolean(offender.getMergedInd()))
                .nfa(ynToBoolean(offender.getNfaInd()))
                .offenderHistoric(Optional.ofNullable(offender.getOffenderHistoric()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .offenderManaged(ynToBoolean(offender.getOffenderManagedInd()))
                .oasysOffenderId(offender.getOffenderPk())
                .ppo(ynToBoolean(offender.getPpoInd()))
                .remand(ynToBoolean(offender.getRemandInd()))
                .riskToOthers(Optional.ofNullable(offender.getRiskToOthers()).map(uk.gov.justice.digital.oasys.jpa.entity.RefElement::getRefElementDesc).orElse(null))
                .riskToSelf(Optional.ofNullable(offender.getRiskToSelf()).map(RefElement::getRefElementDesc).orElse(null))
                .sentence(sentenceOf(offender.getOasysAssessmentGroups()))
                .build();
    }
}