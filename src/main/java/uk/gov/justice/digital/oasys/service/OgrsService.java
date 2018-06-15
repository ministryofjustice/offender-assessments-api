package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OgrsService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OgrsService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    public Optional<List<Ogrs>> getOgrsForOffenderCRN(String crn) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets()
                                .stream()
                                .map(oasysSet -> Ogrs.builder()
                                        .oasysSetId(oasysSet.getOasysSetPk())
                                        .oasysAssessmentGroupId(oasysAssessmentGroup.getOasysAssessmentGroupPk())
                                        .ogrs3_1_year(oasysSet.getOgrs31Year())
                                        .ogrs3_2_year(oasysSet.getOgrs32Year())
                                        .reconvictionRiskSummary(Optional.ofNullable(oasysSet.getOgrs3RiskRecon())
                                                .map(RefElement::getRefElementShortDesc)
                                                .orElse(null))
                                        .reconvictionRisk(Optional.ofNullable(oasysSet.getOgrs3RiskRecon())
                                                .map(RefElement::getRefElementDesc)
                                                .orElse(null))
                                        .completedDate(Optional.ofNullable(oasysSet.getDateCompleted()).map(Timestamp::toLocalDateTime).orElse(null))
                                        .assessmentCompleted(oasysSet.getDateCompleted() != null)
                                        .assessmentVoided(oasysSet.getAssessmentVoidedDate() != null)
                                        .build()))
                .sorted(Comparator.comparing(Ogrs::getCompletedDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList()));
    }

}
