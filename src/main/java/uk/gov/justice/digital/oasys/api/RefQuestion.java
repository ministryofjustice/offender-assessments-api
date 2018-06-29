package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RefQuestion {
    private Long refQuestionId;
    private String refQuestionCode;
    private Long refDisplaySort;
    private String refQuestionText;
    private boolean refMandatoryIndicator;
    private Long refQAWeighting;
    private String refCtAreaEstCode;

    private List<RefAnswer> refAnswers;
}
