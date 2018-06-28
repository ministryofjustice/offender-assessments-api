package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RefSection {
    private Long refSectionId;
    private String refAssVersionCode;
    private Long refVersionCode;
    private String refSectionCode;
    private Long refFormSequence;
    private String refSectionTypeElm;
    private String refSectionTypeCat;
    private Long refCrimNeedScoreThreshold;
    private boolean refScoredForOgp;
    private boolean refScoredForOvp;

    private List<RefQuestion> refQuestions;
}
