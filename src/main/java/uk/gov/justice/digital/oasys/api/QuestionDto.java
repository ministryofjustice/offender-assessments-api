package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionDto {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long oasysQuestionId;
    private Long displayScore;
    private String questionText;
    private AnswerDto answer;

    public static Set<QuestionDto> from(Collection<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions)
                .map(sections -> sections
                        .stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    private static QuestionDto from(OasysQuestion question) {
        if (question == null) {
            return null;
        }
        var refQuestion = Optional.ofNullable(question.getRefQuestion());

        return new QuestionDto(
                refQuestion.map(RefQuestion::getRefQuestionUk).orElse(null),
                refQuestion.map(RefQuestion::getRefQuestionCode).orElse(null),
                question.getOasysQuestionPk(),
                question.getDisplayScore(),
                refQuestion.map(RefQuestion::getRefSectionQuestion).orElse(null),
                AnswerDto.from(question, question.getOasysAnswer()));

    }
}
