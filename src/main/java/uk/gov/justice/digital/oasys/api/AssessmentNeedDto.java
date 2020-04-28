package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.SectionHeader;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class AssessmentNeedDto {
    private SectionHeader section;
    private String name;
    private Boolean overThreshold;
    private Boolean riskOfHarm;
    private Boolean riskOfReoffending;
    private Boolean flaggedAsNeed;

    public static Collection<AssessmentNeedDto> from(Collection<AssessmentNeed> needs) {
        return Optional.ofNullable(needs)
                .map(n -> n
                        .stream()
                        .filter(Objects::nonNull)
                        .map(AssessmentNeedDto::from)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    private static AssessmentNeedDto from(AssessmentNeed need) {
        return new AssessmentNeedDto(
                need.getSection(),
                need.getName(),
                need.getOverThreshold(),
                need.getRiskOfHarm(),
                need.getRiskOfReoffending(),
                need.getFlaggedAsNeed());
    }


}
