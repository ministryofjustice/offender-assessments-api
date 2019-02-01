package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Answer {
    private Long refAnswerId;
    private String refAnswerCode;
    private Long oasysAnswerId;
    private String staticText;
    private String freeformText;
    private Long ogpScore;
    private Long ovpScore;
    private Long qaRawScore;
}

