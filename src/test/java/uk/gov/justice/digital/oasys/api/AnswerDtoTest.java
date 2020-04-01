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
        assertThat(answer.getScore()).isEqualTo(1L);
    }

    @Test
    public void shouldUseOGPScoreForOverallScoreIfPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        OasysAnswer oasysAnswer = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .refSectionAnswer("No")
                .ogpScore(1L)
                .ovpScore(2L).build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);
        assertThat(answer.getScore()).isEqualTo(1L);

    }

    @Test
    public void shouldUseOVPScoreForOverallScoreIfOGPNotPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        OasysAnswer oasysAnswer = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .refSectionAnswer("No")
                .ovpScore(2L).build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);
        assertThat(answer.getScore()).isEqualTo(2L);

    }

    @Test
    public void shouldUseQuestionFreeFormTextIfPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        var answer = AnswerDto.from(question);
        assertThat(answer.getFreeFormText()).isEqualTo("Free form answer");
    }

}