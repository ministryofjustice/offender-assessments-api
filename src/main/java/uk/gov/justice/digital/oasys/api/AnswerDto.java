package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAnswer;

import java.util.Optional;

@Value
public class AnswerDto {
    Long refAnswerId;
    String refAnswerCode;
    Long oasysAnswerId;
    Long displayOrder;
    String staticText;
    String freeFormText;
    Long ogpScore;
    Long ovpScore;
    Long qaRawScore;

    public static AnswerDto from(OasysQuestion question) {
        var oasysAnswer = question.getOasysAnswer();
        if (oasysAnswer == null) {
            return new AnswerDto(null, null, null, null, null, question.getFreeFormatAnswer(), null, null, null);
        }

        var refAnswer = Optional.ofNullable(oasysAnswer.getRefAnswer());
        var questionFromAnswer = (Optional.ofNullable(oasysAnswer.getOasysQuestion()));

        return new AnswerDto(
                refAnswer.map(RefAnswer::getRefAnswerUk).orElse(null),
                refAnswer.map(RefAnswer::getRefAnswerCode).orElse(null),
                oasysAnswer.getOasysAnswerPk(),
                refAnswer.map(RefAnswer::getDisplaySort).orElse(null),
                refAnswer.map(RefAnswer::getRefSectionAnswer).orElse(null),
                questionFromAnswer.map(OasysQuestion::getFreeFormatAnswer).orElse(null),
                refAnswer.map(RefAnswer::getOgpScore).orElse(null),
                refAnswer.map(RefAnswer::getOvpScore).orElse(null),
                refAnswer.map(RefAnswer::getQaRawScore).orElse(null));
    }
}

