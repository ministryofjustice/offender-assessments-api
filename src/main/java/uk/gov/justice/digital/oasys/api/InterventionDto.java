package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjIntervenePivot;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
public class InterventionDto {
    private Boolean copiedForward;
    private String interventionComment;
    private RefElementDto timescale;
    private String interventionCode;
    private String interventionDescription;
    private WhoDoingWorkDto whoDoingWork;

    public static Set<InterventionDto> from(Set<SspObjIntervenePivot> sspObjIntervenePivots) {
        return Optional.ofNullable(sspObjIntervenePivots)
                .map(x -> x.stream()
                        .filter(y -> y.getSspInterventionInSet() != null)
                        .map(SspObjIntervenePivot::getSspInterventionInSet)
                        .map(intervention -> InterventionDto
                                .builder()
                                .copiedForward(DtoUtils.ynToBoolean(intervention.getCopiedForwardIndicator()))
                                .interventionCode(DtoUtils.refElementCode(intervention.getIntervention()))
                                .interventionDescription(DtoUtils.refElementDesc(intervention.getIntervention()))
                                .timescale(RefElementDto.from(intervention.getTimescaleForIntervention()))
                                .interventionComment(intervention.getInterventionComment())
                                .whoDoingWork(WhoDoingWorkDto.from(intervention.getSspWhoDoWorkPivot()))
                                .build())
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }


}
