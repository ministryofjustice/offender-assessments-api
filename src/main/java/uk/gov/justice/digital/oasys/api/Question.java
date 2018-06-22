package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long oasysQuestionId;
    private Long displayScore;
    private String questionText;

    private Answer answer;
}
