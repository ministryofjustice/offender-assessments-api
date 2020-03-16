package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
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
                .defaultDisplayScore(2l)
                .displaySort(5l)
                .ogpScore(1l)
                .ovpScore(2l)
                .qaRawScore(3l)
                .refSectionAnswer("No").build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);

        assertThat(answer.getStaticText()).isEqualTo("No");
        assertThat(answer.getFreeFormText()).isNull();
        assertThat(answer.getRefAnswerCode()).isEqualTo("NO");
        assertThat(answer.getOgpScore()).isEqualTo(1l);
        assertThat(answer.getOvpScore()).isEqualTo(2l);
        assertThat(answer.getQaRawScore()).isEqualTo(3l);
        assertThat(answer.getDisplayOrder()).isEqualTo(5l);
        assertThat(answer.getScore()).isEqualTo(1l);
    }

    @Test
    public void shouldUseOGPScoreForOverallScoreIfPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        OasysAnswer oasysAnswer = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .refSectionAnswer("No")
                .ogpScore(1l)
                .ovpScore(2l).build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);
        assertThat(answer.getScore()).isEqualTo(1l);

    }

    @Test
    public void shouldUseOVPScoreForOverallScoreIfOGPNotPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        OasysAnswer oasysAnswer = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .refSectionAnswer("No")
                .ovpScore(2l).build()).build();

        question.setOasysAnswer(oasysAnswer);
        oasysAnswer.setOasysQuestion(question);

        var answer = AnswerDto.from(question);
        assertThat(answer.getScore()).isEqualTo(2l);

    }

    @Test
    public void shouldUseQuestionFreeFormTextIfPresent() {
        OasysQuestion question = OasysQuestion.builder().freeFormatAnswer("Free form answer").build();

        var answer = AnswerDto.from(question);
        assertThat(answer.getFreeFormText()).isEqualTo("Free form answer");
    }

}