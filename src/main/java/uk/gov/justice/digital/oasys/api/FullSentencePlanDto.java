package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.Section;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Value
public class FullSentencePlanDto {
    Long oasysSetId;
    LocalDateTime createdDate;
    LocalDateTime completedDate;
    Set<ObjectiveDto> objectives;
    Map<String, QuestionDto> questions;

    public static FullSentencePlanDto from(Assessment assessment, Optional<Section> section) {

        if ((assessment.getSspObjectivesInSets().isEmpty()) && section.isEmpty()) {
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
                assessment.getCreateDate(),
                assessment.getDateCompleted(),
                ObjectiveDto.from(assessment.getSspObjectivesInSets()),
                sentencePlanFields);

    }
}
