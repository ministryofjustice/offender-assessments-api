package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder(toBuilder = true)
public class Answer {
    private Long refAnswerId;
    private String refAnswerCode;
    private Long oasysAnswerId;
    private String staticText;
    private String freeformText;
    private Long ogpScore;
    private Long ovpScore;
    private Long qaRawScore;

    @JsonIgnore
    public Long getScore() {
        return Optional.ofNullable(ogpScore).orElse(Optional.ofNullable(ovpScore).orElse(null));
    }
}

