package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private static List<String> PAROLE_SENTENCE_TYPES = List.of("310", "1200", "930", "410");

    @JsonProperty("sentenceCode")
    private String sentenceCode;
    @JsonProperty("sentenceDescription")
    private String sentenceDescription;
    @JsonProperty("custodial")
    private Boolean custodial;
    @JsonProperty("cja")
    private Boolean cja;
    @JsonProperty("orderType")
    private RefElementDto orderType;
    @JsonProperty("offenceBlockType")
    private RefElementDto offenceBlockType;
    @JsonProperty("cjaUnpaidHours")
    private Long cjaUnpaidHours;
    @JsonProperty("cjaSupervisionMonths")
    private Long cjaSupervisionMonths;
    @JsonProperty("activity")
    private String activity;
    @JsonProperty("parolable")
    private Boolean parolable;
    @JsonProperty("offenceDate")
    private LocalDate offenceDate;
    @JsonProperty("sentenceDate")
    private LocalDate sentenceDate;
    @JsonProperty("sentenceLengthCustodyDays")
    private Long sentenceLengthCustodyDays;


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
        return SentenceDto.builder()
                .activity(sentenceDetail.map(OffenceSentenceDetail::getActivityDesc).orElse(null))
                .cja(ynToBoolean(sentence.map(Sentence::getCjaInd).orElse(null)))
                .cjaSupervisionMonths(sentenceDetail.map(OffenceSentenceDetail::getCjaSupervisionMonths).orElse(null))
                .cjaUnpaidHours(sentenceDetail.map(OffenceSentenceDetail::getCjaUnpaidHours).orElse(null))
                .custodial(ynToBoolean(sentence.map(Sentence::getCustodialInd).orElse(null)))
                .orderType(RefElementDto.from(sentence.map(Sentence::getOrderType).orElse(null)))
                .offenceBlockType(RefElementDto.from(offenceBlock.getOffenceBlockType()))
                .sentenceCode(sentence.map(Sentence::getSentenceCode).orElse(null))
                .sentenceDescription(sentence.map(Sentence::getSentenceDesc).orElse(null))
                .parolable(sentence.map(s -> PAROLE_SENTENCE_TYPES.contains(s.getSentenceCode())).orElse(null))
                .offenceDate(offenceBlock.getOffenceDate())
                .sentenceDate(offenceBlock.getSentenceDate())
                .sentenceLengthCustodyDays(offenceBlock.getSentLengthCustDays())
                .build();
    }
}

