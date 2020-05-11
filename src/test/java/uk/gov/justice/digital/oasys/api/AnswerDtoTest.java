package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAnswer;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerDtoTest {

    @Test
    public void shouldReturnAnswerValuesForOASysAnswer() {
        OasysQuestion question = OasysQuestion.builder().build();

        OasysAnswer oasysAnswer = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .defaultDisplayScore(2L)
                .displaySort(5L)
                .ogpScore(1L)
                .ovpScore(2L)
                .qaRawScore(3L)
                .refSectionAnswer("No").build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);

        assertThat(answer.getStaticText()).isEqualTo("No");
        assertThat(answer.getFreeFormText()).isNull();
        assertThat(answer.getRefAnswerCode()).isEqualTo("NO");
        assertThat(answer.getOgpScore()).isEqualTo(1L);
        assertThat(answer.getOvpScore()).isEqualTo(2L);
        assertThat(answer.getQaRawScore()).isEqualTo(3L);
        assertThat(answer.getDisplayOrder()).isEqualTo(5L);
    }

    @Test
    public void shouldUseQuestionFreeFormTextIfPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        var answer = AnswerDto.from(question);
        assertThat(answer.getFreeFormText()).isEqualTo("Free form answer");
    }

}