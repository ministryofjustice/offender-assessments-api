package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import static uk.gov.justice.digital.oasys.api.DtoUtils.ynToBoolean;

@Value
@Builder
public class SentenceDto {

    String sentenceCode;
    String sentenceDescription;
    Boolean custodial;
    Boolean cja;
    RefElementDto orderType;
    RefElementDto offenceBlockType;
    Long cjaUnpaidHours;
    Long cjaSupervisionMonths;
    String activity;
    LocalDate offenceDate;
    LocalDate sentenceDate;
    Long sentenceLengthCustodyDays;

    public static Set<SentenceDto> from(Set<OffenceBlock> offenceBlocks) {
        return Optional.ofNullable(offenceBlocks).orElse(Collections.emptySet())
                .stream()
                .map(SentenceDto::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private static SentenceDto from(OffenceBlock offenceBlock) {
        var sentenceDetail = Optional.ofNullable(offenceBlock.getOffenceSentenceDetail());
        var sentence = Optional.ofNullable(offenceBlock.getSentence());

        return new SentenceDto(
                sentence.map(Sentence::getSentenceCode).orElse(null),
                sentence.map(Sentence::getSentenceDesc).orElse(null),
                ynToBoolean(sentence.map(Sentence::getCustodialInd).orElse(null)),
                ynToBoolean(sentence.map(Sentence::getCjaInd).orElse(null)),
                RefElementDto.from(sentence.map(Sentence::getOrderType).orElse(null)),
                RefElementDto.from(offenceBlock.getOffenceBlockType()),
                sentenceDetail.map(OffenceSentenceDetail::getCjaUnpaidHours).orElse(null),
                sentenceDetail.map(OffenceSentenceDetail::getCjaSupervisionMonths).orElse(null),
                sentenceDetail.map(OffenceSentenceDetail::getActivityDesc).orElse(null),
                offenceBlock.getOffenceDate(),
                offenceBlock.getSentenceDate(),
                offenceBlock.getSentLengthCustDays());

    }
}

