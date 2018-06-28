package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RefQuestion {
    private Long refQuestionId;
    private String refAssVersionCode;
    private Long refVersionId;
    private String refSectionCode;
    private String refQuestionCode;
    private Long refDisplaySort;
    private String refSectionQuestion;
    private boolean refMandatoryIndicator;
    private String refQAWeighting;
    private String refCtAreaEstCode;

    private List<RefAnswer> refAnswers;
}
