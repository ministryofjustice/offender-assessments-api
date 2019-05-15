package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class Question {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long oasysQuestionId;
    private Long displayScore;
    private String questionText;

    @Builder.Default
    private Optional<Answer> answer = Optional.empty();
}
