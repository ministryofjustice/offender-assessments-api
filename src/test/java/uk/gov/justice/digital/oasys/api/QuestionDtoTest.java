package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDtoTest {


    @Test
    public void shouldReturnMapOfQuestionsFromOASysQuestionsSet() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);

        assertThat(questions).containsOnlyKeys("10.98", "10.99", "IP.1");
  }


    @Test
    public void shouldReturnQuestionFreeFormTextIfPresent() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);
        assertThat(questions.get("IP.1").getAnswer().getFreeFormText()).isEqualTo("Free form answer");
    }

    @Test
    public void shouldReturnQuestionValues() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);
        assertThat(questions.get("10.98").getRefQuestionId()).isEqualTo(1l);
        assertThat(questions.get("10.98").getRefQuestionCode()).isEqualTo("10.98");
        assertThat(questions.get("10.98").getDisplayOrder()).isEqualTo(1l);
        assertThat(questions.get("10.98").getQuestionText()).isEqualTo("Question 10.98");
        assertThat(questions.get("10.98").getDisplayScore()).isEqualTo(1l);
    }

    @Test
    public void shouldReturnQuestionAnswerIfPresent() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);

        assertThat(questions).containsOnlyKeys("10.98", "10.99", "IP.1");

        assertThat(questions.get("10.98").getAnswer().getStaticText()).isEqualTo("Yes");
        assertThat(questions.get("10.98").getAnswer().getFreeFormText()).isNull();
        assertThat(questions.get("10.98").getAnswer().getRefAnswerCode()).isEqualTo("YES");
        assertThat(questions.get("10.98").getAnswer().getOgpScore()).isNull();
        assertThat(questions.get("10.98").getAnswer().getOvpScore()).isNull();
        assertThat(questions.get("10.98").getAnswer().getQaRawScore()).isNull();
        assertThat(questions.get("10.98").getAnswer().getDisplayOrder()).isEqualTo(1l);
        assertThat(questions.get("10.98").getAnswer().getScore()).isNull();
    }

    @Test
    public void shouldReturnQuestionForRefQuestion() {

        var refQuestion = RefQuestion.builder()
                .refQuestionUk(1l)
                .refQuestionCode("IP.40")
                .displaySort(1l)
                .refSectionQuestion("Ref Question Text").build();
        var question = QuestionDto.from(refQuestion);

        assertThat(question.getRefQuestionCode()).isEqualTo("IP.40");
        assertThat(question.getDisplayOrder()).isEqualTo(1l);
        assertThat(question.getRefQuestionId()).isEqualTo(1l);
        assertThat(question.getQuestionText()).isEqualTo("Ref Question Text");
    }


}