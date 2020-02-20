package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspCrimNeedObjPivot;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PRIVATE)
@Value
public class CriminogenicNeedDto {
    private String code;
    private String description;
    private Integer priority;

    public static List<CriminogenicNeedDto> from(List<SspCrimNeedObjPivot> sspCrimNeedObjPivots) {
        return Optional.ofNullable(sspCrimNeedObjPivots)
                .map(needs -> needs
                        .stream()
                        .map(need -> CriminogenicNeedDto
                                .builder()
                                .code(DtoUtils.refElementCode(need.getCriminogenicNeed()))
                                .description(DtoUtils.refElementDesc(need.getCriminogenicNeed()))
                                .priority(need.getDisplaySort() != null ? need.getDisplaySort().intValue() : null)
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
