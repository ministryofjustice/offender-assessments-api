package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjIntervenePivot;
import uk.gov.justice.digital.oasys.jpa.entity.SspWhoDoWorkPivot;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Builder(access = AccessLevel.PRIVATE)
@Value
public class WhoDoingWorkDto {
    private String code;
    private String description;
    private String comments;

    public static Set<WhoDoingWorkDto> from(Set<SspWhoDoWorkPivot> sspWhoDoWorkPivot) {


        return Optional.ofNullable(sspWhoDoWorkPivot).orElse(Collections.emptySet())
                .stream()
                .map(work -> WhoDoingWorkDto.builder()
                .code(DtoUtils.refElementCode(work.getWhoDoWork()))
                .comments(work.getComments())
                .description(DtoUtils.refElementDesc(work.getWhoDoWork()))
                .build()).collect(Collectors.toSet());
    }
}