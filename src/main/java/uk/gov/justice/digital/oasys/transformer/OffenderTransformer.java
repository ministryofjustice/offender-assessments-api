package uk.gov.justice.digital.oasys.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.Address;
import uk.gov.justice.digital.oasys.api.Identifiers;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.api.OffenderAlias;
import uk.gov.justice.digital.oasys.api.Sentence;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.OffenceBlock;
import uk.gov.justice.digital.oasys.jpa.entity.OffenceSentenceDetail;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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
                .gender(Optional.ofNullable(offender.getGender()).map(RefElement::getRefElementDesc).orElse(null))
                .identifiers(identifiersOf(offender))
                .lifer(booleanOf(offender.getLifeInd()))
                .limitedAccessOffender(offender.getLimitedAccessOffender())
                .merged(booleanOf(offender.getMergedInd()))
                .nfa(booleanOf(offender.getNfaInd()))
                .offenderHistoric(Optional.ofNullable(offender.getOffenderHistoric()).map(RefElement::getRefElementDesc).orElse(null))
                .offenderManaged(booleanOf(offender.getOffenderManagedInd()))
                .oasysOffenderId(offender.getOffenderPk())
                .ppo(booleanOf(offender.getPpoInd()))
                .remand(booleanOf(offender.getRemandInd()))
                .riskToOthers(Optional.ofNullable(offender.getRiskToOthers()).map(RefElement::getRefElementDesc).orElse(null))
                .riskToSelf(Optional.ofNullable(offender.getRiskToSelf()).map(RefElement::getRefElementDesc).orElse(null))
                .sentence(sentenceOf(offender.getOasysAssessmentGroups()))
                .build();
    }

    private Set<Sentence> sentenceOf(List<OasysAssessmentGroup> oasysAssessmentGroups) {
        Optional<OasysSet> maybeLatestSet = latestOasysSetOf(oasysAssessmentGroups);

        Set<OffenceBlock> offenceBlocks = maybeLatestSet.isPresent() ? maybeLatestSet.get().getOffenceBlock() : new HashSet<>();

        Set<Sentence> sentences = new HashSet<>(offenceBlocks.size());

        for(OffenceBlock block : offenceBlocks)
        {
            Optional<OffenceBlock> offenceBlock = Optional.ofNullable(block);
            var maybeSentenceDetail = offenceBlock.map(OffenceBlock::getOffenceSentenceDetail);
            var maybeSentence = offenceBlock.map(OffenceBlock::getSentence);

            final Optional<Sentence> sentence = offenceBlock.map(b -> Sentence.builder()
                    .activity(maybeSentenceDetail.map(OffenceSentenceDetail::getActivityDesc).orElse(null))
                    .cja(booleanOf(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getCjaInd).orElse(null)))
                    .cjaSupervisionMonths(maybeSentenceDetail.map(OffenceSentenceDetail::getCjaSupervisionMonths).orElse(null))
                    .cjaUnpaidHours(maybeSentenceDetail.map(OffenceSentenceDetail::getCjaUnpaidHours).orElse(null))
                    .custodial(booleanOf(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getCustodialInd).orElse(null)))
                    .endDate(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getEndDate).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate).map(LocalDate::toString).orElse(null))
                    .orderType(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getOrderType).map(RefElement::getRefElementDesc).orElse(null))
                    .sentenceCode(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getSentenceCode).orElse(null))
                    .sentenceDescription(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getSentenceDesc).orElse(null))
                    .startDate(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getStartDate).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate).map(LocalDate::toString).orElse(null))
                    .build());

            if(sentence.isPresent()) {
                sentences.add(sentence.get());
            }
        }



        return sentences;
    }

    public Optional<OasysSet> latestOasysSetOf(List<OasysAssessmentGroup> oasysAssessmentGroups) {
        final Optional<OasysSet> max = Optional.ofNullable(oasysAssessmentGroups).stream()
                .flatMap(Collection::stream)
                .flatMap(oasysAssessmentGroup -> Optional.ofNullable(oasysAssessmentGroup.getOasysSets()).stream())
                .flatMap(Collection::stream)
                .max(Comparator.comparing(OasysSet::getCreateDate));
        return max;
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
