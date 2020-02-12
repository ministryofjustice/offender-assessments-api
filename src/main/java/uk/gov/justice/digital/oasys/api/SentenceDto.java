package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.OffenceBlock;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Value
@Builder
public class SentenceDto {
    private static List<String> PAROLE_SENTENCE_TYPES =  List.of("310", "1200", "930", "410");

    private String sentenceCode;
    private String sentenceDescription;
    private Boolean custodial;
    private Boolean cja;
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderType;
    private Long cjaUnpaidHours;
    private Long cjaSupervisionMonths;
    private String activity;
    private Boolean parolable;

    public static Set<SentenceDto> from(List<OasysAssessmentGroup> oasysAssessmentGroups) {
        var latestSet = oasysAssessmentGroups.stream()
                .flatMap(g -> g.getOasysSets().stream())
                .max(Comparator.comparing(OasysSet::getCreateDate));
        var latestOffenceBlock = latestSet.isPresent() ? latestSet.get().getOffenceBlock() : new HashSet<OffenceBlock>();

        return latestOffenceBlock.stream().filter(Objects::nonNull).map(SentenceDto::from).collect(Collectors.toSet());
    }

    private static SentenceDto from(OffenceBlock offenceBlock) {
        var sentenceDetail = offenceBlock.getOffenceSentenceDetail();
        var sentence = offenceBlock.getSentence();

        return SentenceDto.builder()
                .activity(sentenceDetail.getActivityDesc())
                .cja(ynToBoolean(sentence.getCjaInd()))
                .cjaSupervisionMonths(sentenceDetail.getCjaSupervisionMonths())
                .cjaUnpaidHours(sentenceDetail.getCjaUnpaidHours())
                .custodial(ynToBoolean(sentence.getCustodialInd()))
                .endDate(sentence.getEndDate())
                .orderType(refElementDesc(sentence.getOrderType()))
                .sentenceCode(sentence.getSentenceCode())
                .sentenceDescription(sentence.getSentenceDesc())
                .startDate(sentence.getStartDate())
                .parolable(PAROLE_SENTENCE_TYPES.contains(sentence.getSentenceCode()))
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

