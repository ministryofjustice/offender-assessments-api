package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class QuestionDto {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long oasysQuestionId;
    private Long displayOrder;
    private Long displayScore;
    private String questionText;
    private AnswerDto answer;

    public QuestionDto() {
        refQuestionId = null;
        refQuestionCode = null;
        oasysQuestionId = null;
        displayScore = null;
        questionText = null;
        answer = null;
    }

    public QuestionDto(Long refQuestionUk, String refQuestionCode, Long displaySort, String refSectionQuestion) {
        this.refQuestionId = refQuestionUk;
        this.refQuestionCode = refQuestionCode;
        this.displayOrder = displaySort;
        this.questionText = refSectionQuestion;
    }

    public static Map<String, QuestionDto> from(Set<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions)
                .map(sections -> sections
                        .stream()
                        .map(QuestionDto::from)
                        .collect(Collectors.toMap(QuestionDto::getRefQuestionCode, question -> question)))
                .orElse(null);
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
