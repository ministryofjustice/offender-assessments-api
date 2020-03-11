package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSection;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class FullSentencePlanDto {
    private Long oasysSetId;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;
    private Set<ObjectiveDto> objectives;
    private Map<String, QuestionDto> questions;

    public static FullSentencePlanDto from(OasysSet oasysSet) {

        if (oasysSet.getSspObjectivesInSets() == null || oasysSet.getSspObjectivesInSets().isEmpty()) {
            return null;
        }
        var questions = Optional.ofNullable(oasysSet.getOasysSections()).orElse(Collections.emptySet()).stream()
                .filter(s->s.getRefSection().getRefSectionCode().equals("ISP") || s.getRefSection().getRefSectionCode().equals("RSP")).findFirst() //get the section
                .map(OasysSection::getOasysQuestions).orElse(Collections.emptySet()).stream() // get the questions for the section
                .sorted(Comparator.comparingLong(q-> q.getRefQuestion().getDisplaySort())) // sort by display order
                .collect(Collectors.toList());

        Map<String, QuestionDto> sentencePlanFields = new HashMap<>();
        questions.forEach(question -> sentencePlanFields.put(question.getRefQuestion().getRefQuestionCode(), QuestionDto.from(question)));

        return new FullSentencePlanDto(
                oasysSet.getOasysSetPk(),
                earliestSspObjectiveOf(oasysSet.getSspObjectivesInSets()),
                oasysSet.getDateCompleted(),
                ObjectiveDto.from(oasysSet.getSspObjectivesInSets()),
                sentencePlanFields);

    }

    private static LocalDateTime earliestSspObjectiveOf(Set<SspObjectivesInSet> sspObjectivesInSets) {
        return sspObjectivesInSets.stream()
                .min(Comparator.comparing(SspObjectivesInSet::getCreateDate))
                .map(SspObjectivesInSet::getCreateDate)
                .orElse(null);
    }
}
