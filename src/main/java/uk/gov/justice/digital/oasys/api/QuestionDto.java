package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionDto {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long oasysQuestionId;
    private Long displayOrder;
    private Long displayScore;
    private String questionText;
    private AnswerDto answer;

    public QuestionDto(Long refQuestionUk, String refQuestionCode, Long displaySort, String refSectionQuestion) {
        this.refQuestionId = refQuestionUk;
        this.refQuestionCode = refQuestionCode;
        this.displayOrder = displaySort;
        this.questionText = refSectionQuestion;
    }

    public static Set<QuestionDto> from(Collection<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions).orElse(Collections.emptySet())
                        .stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toSet());
    }

    public static QuestionDto from(OasysQuestion question) {
        if (question == null) {
            return null;
        }
        var refQuestion = Optional.ofNullable(question.getRefQuestion());

        return new QuestionDto(
                refQuestion.map(RefQuestion::getRefQuestionUk).orElse(null),
                refQuestion.map(RefQuestion::getRefQuestionCode).orElse(null),
                question.getOasysQuestionPk(),
                refQuestion.map(RefQuestion::getDisplaySort).orElse(null),
                question.getDisplayScore(),
                refQuestion.map(RefQuestion::getRefSectionQuestion).orElse(null),
                AnswerDto.from(question));
    }

    public static QuestionDto from(RefQuestion refQuestion) {
        if (refQuestion == null) {
            return null;
        }
        return new QuestionDto(
                refQuestion.getRefQuestionUk(),
                refQuestion.getRefQuestionCode(),
                refQuestion.getDisplaySort(),
                refQuestion.getRefSectionQuestion());
    }
}
