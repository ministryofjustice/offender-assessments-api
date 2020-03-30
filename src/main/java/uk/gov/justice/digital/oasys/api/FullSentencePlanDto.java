package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;

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

    public static FullSentencePlanDto from(Assessment assessment, Optional<Section> section) {

        if (assessment.getSspObjectivesInSets() == null || assessment.getSspObjectivesInSets().isEmpty()) {
            return null;
        }
        
        Map<String, QuestionDto> sentencePlanFields = new HashMap<>();
        if(section.isPresent()) {
            var questions = section.get().getOasysQuestions().stream() // get the questions for the section
                    .sorted(Comparator.comparingLong(q -> q.getRefQuestion().getDisplaySort())) // sort by display order
                    .collect(Collectors.toList());
            questions.forEach(question -> sentencePlanFields.put(question.getRefQuestion().getRefQuestionCode(), QuestionDto.from(question)));

            if(Objects.nonNull(section.get().getRefSection())) {
                section.get().getRefSection().getRefQuestions().forEach(questionRef ->
                        sentencePlanFields.putIfAbsent(questionRef.getRefQuestionCode(), QuestionDto.from(questionRef)));
            }
        }

        return new FullSentencePlanDto(
                assessment.getOasysSetPk(),
                earliestSspObjectiveOf(assessment.getSspObjectivesInSets()),
                assessment.getDateCompleted(),
                ObjectiveDto.from(assessment.getSspObjectivesInSets()),
                sentencePlanFields);

    }

    private static LocalDateTime earliestSspObjectiveOf(Set<SspObjectivesInSet> sspObjectivesInSets) {
        return sspObjectivesInSets.stream()
                .min(Comparator.comparing(SspObjectivesInSet::getCreateDate))
                .map(SspObjectivesInSet::getCreateDate)
                .orElse(null);
    }
}
