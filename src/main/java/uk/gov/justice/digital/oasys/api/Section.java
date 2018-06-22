package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Section {
    private String refSectionCode;
    private Long oasysSectionId;
    private String statusCode;
    private String statusDescription;
    private Integer sectionOgpWeightedScore;
    private Integer sectionOgpRawScore;
    private Integer sectionOvpWeightedScore;
    private Integer sectionOvpRawScore;
    private Integer sectionOtherWeightedScore;
    private Integer sectionOtherRawScore;
    private Boolean lowScoreAttentionNeeded;

    private List<Question> questions;
}
