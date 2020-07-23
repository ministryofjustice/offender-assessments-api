package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjIntervenePivot;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class InterventionDto {
    Boolean copiedForward;
    String interventionComment;
    RefElementDto timescale;
    String interventionCode;
    String interventionDescription;
    Set<WhoDoingWorkDto> whoDoingWork;
    InterventionMeasureDto interventionMeasure;

    public static Set<InterventionDto> from(Set<SspObjIntervenePivot> sspObjIntervenePivots) {
        return Optional.ofNullable(sspObjIntervenePivots).orElse(Collections.emptySet()).stream()
                        .filter(y -> y.getSspInterventionInSet() != null)
                        .map(SspObjIntervenePivot::getSspInterventionInSet)
                        .map(intervention -> new InterventionDto(
                                DtoUtils.ynToBoolean(intervention.getCopiedForwardIndicator()),
                                intervention.getInterventionComment(),
                                RefElementDto.from(intervention.getTimescaleForIntervention()),
                                DtoUtils.refElementCode(intervention.getIntervention()),
                                DtoUtils.refElementDesc(intervention.getIntervention()),
                                WhoDoingWorkDto.from(intervention.getSspWhoDoWorkPivot()),
                               InterventionMeasureDto.from(intervention.getSspInterventionMeasure())))
                        .collect(Collectors.toSet());
    }


}
