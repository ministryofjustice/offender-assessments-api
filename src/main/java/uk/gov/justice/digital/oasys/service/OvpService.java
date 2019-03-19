package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ovp;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OvpService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OvpService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    public Optional<List<Ovp>> getOvpForOasysOffenderPk(Long oasysOffenderPk) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderPk);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ovp>> getOvpForOffenderCRN(String crn) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ovp>> getOvpForOffenderPNC(String pnc) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ovp>> getOvpForOffenderNomisId(String nomisId) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ovp>> getOvpForOffenderBookingId(String bookingId) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return mapOffender(maybeOffender);
    }

    private Optional<List<Ovp>> mapOffender(Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets()
                                .stream()
                                .map(oasysSet -> Ovp.builder()
                                        .oasysSetId(oasysSet.getOasysSetPk())
                                        .oasysAssessmentGroupId(oasysAssessmentGroup.getOasysAssessmentGroupPk())
                                        .ovpStaticWeightedScore(oasysSet.getOvpStWesc())
                                        .ovpDynamicWeightedScore(oasysSet.getOvpDyWesc())
                                        .ovpTotalWeightedScore(oasysSet.getOvpTotWesc())
                                        .ovp1Year(oasysSet.getOvp1Year())
                                        .ovp2Year(oasysSet.getOvp2Year())
                                        .ovpRiskSummary(Optional.ofNullable(oasysSet.getOvpRiskRecon())
                                                .map(RefElement::getRefElementShortDesc)
                                                .orElse(null))
                                        .ovpRisk(Optional.ofNullable(oasysSet.getOvpRiskRecon())
                                                .map(RefElement::getRefElementDesc)
                                                .orElse(null))
                                        .ovpPreviousWeightedScore(oasysSet.getOvpPrevWesc())
                                        .ovpViolentWeightedScore(oasysSet.getOvpVioWesc())
                                        .ovpNonViolentWeightedScore(oasysSet.getOvpNonVioWesc())
                                        .ovpAgeWeightedScore(oasysSet.getOvpAgeWesc())
                                        .ovpSexWeightedScore(oasysSet.getOvpSexWesc())
                                        .completedDate(Optional.ofNullable(oasysSet.getDateCompleted()).map(Timestamp::toLocalDateTime).orElse(null))
                                        .assessmentCompleted(oasysSet.getDateCompleted() != null)
                                        .assessmentVoided(oasysSet.getAssessmentVoidedDate() != null)
                                        .build()))
                .sorted(Comparator.comparing(Ovp::getCompletedDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList()));
    }
}

