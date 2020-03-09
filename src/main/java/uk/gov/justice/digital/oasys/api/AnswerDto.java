package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAnswer;

import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerDto {
    private Long refAnswerId;
    private String refAnswerCode;
    private Long oasysAnswerId;
    private Long displayOrder;
    private String staticText;
    private String freeFormText;
    private Long ogpScore;
    private Long ovpScore;
    private Long qaRawScore;

    public static AnswerDto from(OasysQuestion question) {
        if (question.getOasysAnswer() == null) {
            return new AnswerDto(null, null, null,null,null, question.getFreeFormatAnswer(), null, null, null);
        }

        var refAnswer = Optional.ofNullable(question.getOasysAnswer().getRefAnswer());
        var questionFromAnswer = (Optional.ofNullable(question.getOasysAnswer().getOasysQuestion()));

        return new AnswerDto(
                refAnswer.map(RefAnswer::getRefAnswerUk).orElse(null),
                refAnswer.map(RefAnswer::getRefAnswerCode).orElse(null),
                question.getOasysAnswer().getOasysAnswerPk(),
                refAnswer.map(RefAnswer::getDisplaySort).orElse(null),
                refAnswer.map(RefAnswer::getRefSectionAnswer).orElse(null),
                questionFromAnswer.map(OasysQuestion::getFreeFormatAnswer).orElse(null),
                refAnswer.map(RefAnswer::getOgpScore).orElse(null),
                refAnswer.map(RefAnswer::getOvpScore).orElse(null),
                refAnswer.map(RefAnswer::getQaRawScore).orElse(null));
    }

    @JsonIgnore
    public Long getScore() {
        return Optional.ofNullable(ogpScore).orElse(Optional.ofNullable(ovpScore).orElse(null));
    }
}

