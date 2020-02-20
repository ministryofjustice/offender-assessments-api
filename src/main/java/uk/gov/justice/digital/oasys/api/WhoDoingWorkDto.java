package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspWhoDoWorkPivot;


@Builder(access = AccessLevel.PRIVATE)
@Value
public class WhoDoingWorkDto {
    private String code;
    private String description;
    private String comments;

    public static WhoDoingWorkDto from(SspWhoDoWorkPivot sspWhoDoWorkPivot) {
        if (sspWhoDoWorkPivot == null) {
            return null;
        }
        return WhoDoingWorkDto.builder()
                .code(DtoUtils.refElementCode(sspWhoDoWorkPivot.getWhoDoWork()))
                .comments(sspWhoDoWorkPivot.getComments())
                .description(DtoUtils.refElementDesc(sspWhoDoWorkPivot.getWhoDoWork()))
                .build();
    }
}