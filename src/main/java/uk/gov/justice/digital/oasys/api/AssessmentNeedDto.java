package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.Section;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public
class AssessmentNeedDto {

    private Section section;
    private Boolean overThreshold;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean flaggedAsNeed;

    public static List<AssessmentNeedDto> from(Stream<AssessmentNeed> needs) {
        return Optional.ofNullable(needs)
                .map(need -> needs
                        .map(AssessmentNeedDto::from)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private static AssessmentNeedDto from(AssessmentNeed need) {
        return new AssessmentNeedDto(
                need.getSection(),
                need.getOverThreshold(),
                need.getRiskOfHarm(),
                need.getRiskOfReoffending(),
                need.getFlaggedAsNeed());
    }



}
