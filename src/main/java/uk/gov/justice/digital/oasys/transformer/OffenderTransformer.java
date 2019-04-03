package uk.gov.justice.digital.oasys.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.Address;
import uk.gov.justice.digital.oasys.api.Identifiers;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.api.OffenderAlias;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OffenderTransformer {
    public Offender offenderOf(uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return Offender.builder()
                .address(addressOf(offender))
                .aliases(aliasesOf(offender))
                .cmsEventNumber(offender.getCmsEventNumber())
                .custody(booleanOf(offender.getCustodyInd()))
                .dateOfBirth(localDateOf(offender.getDateOfBirth()))
                .dateOfDeath(localDateOf(offender.getDateOfDeath()))
                .dateOfDeportation(localDateOf(offender.getDateOfDeportation()))
                .deceased(booleanOf(offender.getDeceasedInd()))
                .dischargeCode(Optional.ofNullable(offender.getDischargeCode()).map(RefElement::getRefElementDesc).orElse(null))
                .ethnicCategory(Optional.ofNullable(offender.getEthnicCategory()).map(RefElement::getRefElementDesc).orElse(null))
                .familyName(offender.getFamilyName())
                .forename1(offender.getForename1())
                .forename2(offender.getForename2())
                .forename3(offender.getForename3())
                .gender(Optional.ofNullable(offender.getEthnicCategory()).map(RefElement::getRefElementDesc).orElse(null))
                .identifiers(identifiersOf(offender))
                .lifer(booleanOf(offender.getLifeInd()))
                .limitedAccessOffender(offender.getLimitedAccessOffender())
                .merged(booleanOf(offender.getMergedInd()))
                .nfa(booleanOf(offender.getNfaInd()))
                .offenderHistoric(Optional.ofNullable(offender.getOffenderHistoric()).map(RefElement::getRefElementDesc).orElse(null))
                .offenderManaged(booleanOf(offender.getOffenderManagedInd()))
                .offenderPk(offender.getOffenderPk())
                .ppo(booleanOf(offender.getPpoInd()))
                .remand(booleanOf(offender.getRemandInd()))
                .riskToOthers(Optional.ofNullable(offender.getRiskToOthers()).map(RefElement::getRefElementDesc).orElse(null))
                .riskToSelf(Optional.ofNullable(offender.getRiskToSelf()).map(RefElement::getRefElementDesc).orElse(null))
                .build();
    }

    private LocalDate localDateOf(Timestamp date) {
        return Optional.ofNullable(date).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null);
    }

    private Identifiers identifiersOf(uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return Identifiers.builder()
                .bookingNumber(offender.getPrisonNumber())
                .crn(offender.getCmsProbNumber())
                .pnc(offender.getPnc())
                .croNumber(offender.getCroNumber())
                .legacyCmsProbNumber(offender.getLegacyCmsProbNumber())
                .mergePncNumber(offender.getMergePncNumber())
                .nomisId(offender.getCmsPrisNumber())
                .build();
    }

    private Boolean booleanOf(String yn) {
        return Optional.ofNullable(yn).map(s -> !s.equals("N")).orElse(null);
    }

    private List<OffenderAlias> aliasesOf(uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return Optional.ofNullable(offender.getOffenderAliases()).map(
                aliases -> aliases.stream().map(a -> OffenderAlias
                        .builder()
                        .dateOfBirth(localDateOf(a.getAliasDateOfBirth()))
                        .familyName(a.getAliasFamilyName())
                        .forename1(a.getAliasForename1())
                        .forename2(a.getAliasForename2())
                        .offenderAliasPk(a.getOffenderAliasPk())
                        .build()).collect(Collectors.toList())).orElse(null);
    }

    public Address addressOf(uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return Address.builder()
                .addressLine1(offender.getAddressLine1())
                .addressLine2(offender.getAddressLine2())
                .addressLine3(offender.getAddressLine3())
                .addressLine4(offender.getAddressLine4())
                .addressLine5(offender.getAddressLine5())
                .addressLine6(offender.getAddressLine6())
                .addressPostcode(offender.getAddressPostcode())
                .telephoneNumber(offender.getTelephoneNumber())
                .build();
    }
}
