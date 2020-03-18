package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefQuestion;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDtoTest {


    @Test
    public void shouldReturnMapOfQuestionsFromOASysQuestionsSet() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);

        var validQuestions = Set.of("10.98", "10.99", "IP.1");
        assertThat(questions.stream().filter(q -> !validQuestions.contains(q.getRefQuestionCode()))).isEmpty();
  }


    @Test
    public void shouldReturnQuestionFreeFormTextIfPresent() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);
        var question = get(questions, "IP.1");
        assertThat(question.getAnswer().getFreeFormText()).isEqualTo("Free form answer");
    }

    @Test
    public void shouldReturnQuestionValues() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);
        var question = get(questions, "10.98");

        assertThat(question.getRefQuestionId()).isEqualTo(1l);
        assertThat(question.getRefQuestionCode()).isEqualTo("10.98");
        assertThat(question.getDisplayOrder()).isEqualTo(1l);
        assertThat(question.getQuestionText()).isEqualTo("Question 10.98");
        assertThat(question.getDisplayScore()).isEqualTo(1l);
    }

    @Test
    public void shouldReturnQuestionAnswerIfPresent() {

        var oasysQuestions = ControllerServiceTestContext.getOASysQuestions();

        var questions = QuestionDto.from(oasysQuestions);

        var validQuestions = Set.of("10.98", "10.99", "IP.1");
        assertThat(questions.stream().filter(q -> !validQuestions.contains(q.getRefQuestionCode()))).isEmpty();

        var question = get(questions, "10.98");
        assertThat(question.getAnswer().getStaticText()).isEqualTo("Yes");
        assertThat(question.getAnswer().getFreeFormText()).isNull();
        assertThat(question.getAnswer().getRefAnswerCode()).isEqualTo("YES");
        assertThat(question.getAnswer().getOgpScore()).isNull();
        assertThat(question.getAnswer().getOvpScore()).isNull();
        assertThat(question.getAnswer().getQaRawScore()).isNull();
        assertThat(question.getAnswer().getDisplayOrder()).isEqualTo(1l);
        assertThat(question.getAnswer().getScore()).isNull();
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

    private QuestionDto get(Collection<QuestionDto> questions, String key) {
        return questions.stream().filter(q -> q.getRefQuestionCode().equals(key)).findFirst().get();
    }
}