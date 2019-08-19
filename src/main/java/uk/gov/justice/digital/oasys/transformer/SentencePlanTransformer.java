package uk.gov.justice.digital.oasys.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.CriminogenicNeed;
import uk.gov.justice.digital.oasys.api.Intervention;
import uk.gov.justice.digital.oasys.api.Objective;
import uk.gov.justice.digital.oasys.api.ObjectiveMeasure;
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.api.RefElement;
import uk.gov.justice.digital.oasys.api.WhoDoingWork;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspCrimNeedObjPivot;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjIntervenePivot;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjective;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectiveMeasure;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspWhoDoWorkPivot;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SentencePlanTransformer {

    public Optional<ProperSentencePlan> sentencePlanOf(OasysSet oasysSet) {
        return Optional.ofNullable(oasysSet)
                .map(os ->
                        ProperSentencePlan
                                .builder()
                                .oasysSetId(oasysSet.getOasysSetPk())
                                .completedDate(oasysSet.getDateCompleted().toLocalDateTime().toLocalDate())
                                .objectives(objectivesOf(oasysSet.getSspObjectivesInSets()))
                                .createdDate(earliestSspObjectveOf(os.getSspObjectivesInSets()))
                                .build())
                .filter(psp -> psp.getObjectives() != null && !psp.getObjectives().isEmpty());
    }

    private LocalDate earliestSspObjectveOf(List<SspObjectivesInSet> sspObjectivesInSets) {
        return Optional.ofNullable(sspObjectivesInSets).flatMap(s -> s.stream()
                .min(Comparator.comparing(SspObjectivesInSet::getCreateDate)))
                .map(SspObjectivesInSet::getCreateDate)
                .map(t -> t.toLocalDateTime().toLocalDate())
                .orElse(null);
    }

    private List<Objective> objectivesOf(List<SspObjectivesInSet> sspObjectivesInSets) {

        return Optional.ofNullable(sspObjectivesInSets).map(sspois -> sspois.stream()
                .map(sspo -> Objective
                        .builder()
                        .criminogenicNeeds(criminogenicNeedsOf(sspo.getSspCrimNeedObjPivots()))
                        .howMeasured(sspo.getHowProgressMeasured())
                        .interventions(interventionsOf(sspo.getSspObjIntervenePivots()))
                        .objectiveCode(objectiveCodeOf(sspo.getSspObjective()))
                        .objectiveDescription(objectiveDescriptionOf(sspo.getSspObjective()))
                        .objectiveMeasure(objectiveMeasureOf(sspo.getSspObjectiveMeasure()))
                        .objectiveType(objectiveTypeOf(sspo.getObjectiveType()))
                        .whoDoingWork(whoDoingWorkOf(sspo.getSspWhoDoWorkPivot()))
                        .build())
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private WhoDoingWork whoDoingWorkOf(SspWhoDoWorkPivot sspWhoDoWorkPivot) {
        return Optional.ofNullable(sspWhoDoWorkPivot).map(p -> WhoDoingWork.builder()
                .code(p.getWhoDoWork().getRefElementCode())
                .comments(p.getComments())
                .description(p.getWhoDoWork().getRefElementDesc())
                .build()).orElse(null);
    }

    private RefElement objectiveTypeOf(uk.gov.justice.digital.oasys.jpa.entity.RefElement objectiveType) {
        return Optional.ofNullable(objectiveType).map(ot -> RefElement
                .builder()
                .shortDescription(ot.getRefElementShortDesc())
                .code(ot.getRefElementCode())
                .description(ot.getRefElementDesc())
                .build()).orElse(null);
    }

    private ObjectiveMeasure objectiveMeasureOf(SspObjectiveMeasure maybeSspObjectiveMeasure) {
        return Optional.ofNullable(maybeSspObjectiveMeasure).map(
                sspObjectiveMeasure -> ObjectiveMeasure.builder()
                        .comments(sspObjectiveMeasure.getObjectiveStatusComments())
                        .status(statusOf(sspObjectiveMeasure.getObjectiveStatus()))
                        .build()).orElse(null);
    }

    private RefElement statusOf(uk.gov.justice.digital.oasys.jpa.entity.RefElement objectiveStatus) {
        return Optional.ofNullable(objectiveStatus).map(os -> RefElement
                .builder()
                .description(os.getRefElementDesc())
                .code(os.getRefElementCode())
                .shortDescription(os.getRefElementShortDesc())
                .build()).orElse(null);
    }

    private String objectiveDescriptionOf(SspObjective sspObjective) {
        return objectiveOf(sspObjective)
                .map(uk.gov.justice.digital.oasys.jpa.entity.Objective::getObjectiveDesc).orElse(null);
    }

    private String objectiveCodeOf(SspObjective sspObjective) {
        return objectiveOf(sspObjective)
                .map(uk.gov.justice.digital.oasys.jpa.entity.Objective::getObjectiveCode).orElse(null);
    }

    private Optional<uk.gov.justice.digital.oasys.jpa.entity.Objective> objectiveOf(SspObjective sspObjective) {
        return Optional.ofNullable(sspObjective)
                .flatMap(sspObjective1 -> Optional.ofNullable(sspObjective1.getObjective()));
    }

    private List<Intervention> interventionsOf(List<SspObjIntervenePivot> sspObjIntervenePivots) {
        return Optional.ofNullable(sspObjIntervenePivots)
                .map(x -> x.stream()
                        .filter(y -> y.getSspInterventionInSet() != null)
                        .map(SspObjIntervenePivot::getSspInterventionInSet)
                        .map(intervention -> Intervention
                                .builder()
                                .copiedForward("Y".equals(intervention.getCopiedForwardIndicator()))
                                .interventionCode(intervention.getIntervention().getRefElementCode())
                                .interventionDescription(intervention.getIntervention().getRefElementDesc())
                                .timescale(timescaleOf(intervention.getTimescaleForIntervention()))
                                .interventionComment(intervention.getInterventionComment())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private RefElement timescaleOf(uk.gov.justice.digital.oasys.jpa.entity.RefElement timescaleForIntervention) {
        return Optional.ofNullable(timescaleForIntervention).map(t -> RefElement
                .builder()
                .code(t.getRefElementCode())
                .description(t.getRefElementDesc())
                .build())
                .orElse(null);
    }

    private List<CriminogenicNeed> criminogenicNeedsOf(List<SspCrimNeedObjPivot> sspCrimNeedObjPivots) {
        return Optional.ofNullable(sspCrimNeedObjPivots)
                .map(needs -> needs
                        .stream()
                        .map(need -> CriminogenicNeed
                                .builder()
                                .code(need.getCriminogenicNeed().getRefElementCode())
                                .description(need.getCriminogenicNeed().getRefElementDesc())
                                .priority(need.getDisplaySort().intValue())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
