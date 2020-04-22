package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class RefQuestionDto {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long refDisplaySort;
    private String refQuestionText;
    private boolean refMandatoryIndicator;
    private Long refQAWeighting;
    private String refCtAreaEstCode;

    private List<RefAnswerDto> refAnswers;

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
