package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Section {
    private Long refSectionId;
    private String refSectionCode;
    private Long oasysSectionId;
    private String status;
    private Long sectionOgpWeightedScore;
    private Long sectionOgpRawScore;
    private Long sectionOvpWeightedScore;
    private Long sectionOvpRawScore;
    private Long sectionOtherWeightedScore;
    private Long sectionOtherRawScore;
    private Boolean lowScoreAttentionNeeded;

    private List<Question> questions;
}
