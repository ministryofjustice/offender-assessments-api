package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Value
@Builder
public class RefQuestionDto {
    Long refQuestionId;
    String refQuestionCode;
    Long refDisplaySort;
    String refQuestionText;
    boolean refMandatoryIndicator;
    Long refQAWeighting;
    String refCtAreaEstCode;
    List<RefAnswerDto> refAnswers;

    public static List<RefQuestionDto> from(List<RefQuestion> refQuestions) {
        return Optional.ofNullable(refQuestions).orElse(Collections.emptyList())
                .stream()
                .map(RefQuestionDto::from)
                .collect(Collectors.toList());

    }

    private static RefQuestionDto from(uk.gov.justice.digital.oasys.jpa.entity.RefQuestion refQuestion) {
        return RefQuestionDto.builder()
                .refDisplaySort(refQuestion.getDisplaySort())
                .refMandatoryIndicator(DtoUtils.ynToBoolean(refQuestion.getMandatoryInd()))
                .refQAWeighting(refQuestion.getQaWeighting())
                .refQuestionCode(refQuestion.getRefQuestionCode())
                .refQuestionId(refQuestion.getRefQuestionUk())
                .refQuestionText(refQuestion.getRefSectionQuestion())
                .refAnswers(RefAnswerDto.from(refQuestion.getRefAnswers()))
                .build();
    }
}
