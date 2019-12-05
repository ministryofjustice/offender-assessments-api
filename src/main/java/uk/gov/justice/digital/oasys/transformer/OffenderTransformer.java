package uk.gov.justice.digital.oasys.transformer;

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

import static uk.gov.justice.digital.oasys.transformer.TypesTransformer.ynToBoolean;

public class OffenderTransformer {

    private static List<String> PAROLE_SENTENCE_TYPES =  List.of("310", "1200", "930", "410");

    public static Set<Sentence> sentenceOf(List<OasysAssessmentGroup> oasysAssessmentGroups) {
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
                    .cja(ynToBoolean(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getCjaInd).orElse(null)))
                    .cjaSupervisionMonths(maybeSentenceDetail.map(OffenceSentenceDetail::getCjaSupervisionMonths).orElse(null))
                    .cjaUnpaidHours(maybeSentenceDetail.map(OffenceSentenceDetail::getCjaUnpaidHours).orElse(null))
                    .custodial(ynToBoolean(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getCustodialInd).orElse(null)))
                    .endDate(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getEndDate).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate).map(LocalDate::toString).orElse(null))
                    .orderType(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getOrderType).map(RefElement::getRefElementDesc).orElse(null))
                    .sentenceCode(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getSentenceCode).orElse(null))
                    .sentenceDescription(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getSentenceDesc).orElse(null))
                    .startDate(maybeSentence.map(uk.gov.justice.digital.oasys.jpa.entity.Sentence::getStartDate).map(Timestamp::toLocalDateTime).map(LocalDateTime::toLocalDate).map(LocalDate::toString).orElse(null))
                    .parolable(maybeSentence.map(s-> PAROLE_SENTENCE_TYPES.contains(s.getSentenceCode())).orElse(false))
                    .build());

            sentence.ifPresent(sentences::add);
        }



        return sentences;
    }

    private static Optional<OasysSet> latestOasysSetOf(List<OasysAssessmentGroup> oasysAssessmentGroups) {
        return Optional.ofNullable(oasysAssessmentGroups).stream()
                .flatMap(Collection::stream)
                .flatMap(oasysAssessmentGroup -> Optional.ofNullable(oasysAssessmentGroup.getOasysSets()).stream())
                .flatMap(Collection::stream)
                .max(Comparator.comparing(OasysSet::getCreateDate));
    }

}