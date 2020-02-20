package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OgpService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OgpService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    public Optional<List<Ogp>> getOgpForOasysOffenderPk(Long oasysOffenderPk) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderPk);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ogp>> getOgpForOffenderCRN(String crn) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ogp>> getOgpForOffenderPNC(String pnc) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ogp>> getOgpForOffenderNomisId(String nomisId) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return mapOffender(maybeOffender);
    }

    public Optional<List<Ogp>> getOgpForOffenderBookingId(String bookingId) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return mapOffender(maybeOffender);
    }


    private Optional<List<Ogp>> mapOffender(Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets()
                                .stream()
                                .map(oasysSet -> Ogp.builder()
                                        .oasysSetId(oasysSet.getOasysSetPk())
                                        .oasysAssessmentGroupId(oasysAssessmentGroup.getOasysAssessmentGroupPk())
                                        .ogp1Year(oasysSet.getOgp1Year())
                                        .ogp2Year(oasysSet.getOgp2Year())
                                        .ogpDynamicWeightedScore(oasysSet.getOgpDyWesc())
                                        .ogpStaticWeightedScore(oasysSet.getOgpStWesc())
                                        .ogpTotalWeightedScore(oasysSet.getOgpTotWesc())
                                        .ogpRiskSummary(Optional.ofNullable(oasysSet.getOgpRiskRecon())
                                                .map(RefElement::getRefElementShortDesc)
                                                .orElse(null))
                                        .ogpRisk(Optional.ofNullable(oasysSet.getOgpRiskRecon())
                                                .map(RefElement::getRefElementDesc)
                                                .orElse(null))
                                        .completedDate(oasysSet.getDateCompleted())
                                        .assessmentCompleted(oasysSet.getDateCompleted() != null)
                                        .assessmentVoided(oasysSet.getAssessmentVoidedDate() != null)
                                        .build()))
                .sorted(Comparator.comparing(Ogp::getCompletedDate, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList()));
    }

}

