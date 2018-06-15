package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.sql.Timestamp;
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

    public Optional<List<Ogp>> getOgpForOffenderCRN(String crn) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

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
                                        .ogpDyWesc(oasysSet.getOgpDyWesc())
                                        .ogpStWesc(oasysSet.getOgpStWesc())
                                        .ogpTotWesc(oasysSet.getOgpTotWesc())
                                        .riskSummary(Optional.ofNullable(oasysSet.getOgrs3RiskRecon())
                                                .map(RefElement::getRefElementShortDesc)
                                                .orElse(null))
                                        .risk(Optional.ofNullable(oasysSet.getOgrs3RiskRecon())
                                                .map(RefElement::getRefElementDesc)
                                                .orElse(null))
                                        .completedDate(Optional.ofNullable(oasysSet.getDateCompleted()).map(Timestamp::toLocalDateTime).orElse(null))
                                        .assessmentCompleted(oasysSet.getDateCompleted() != null)
                                        .assessmentVoided(oasysSet.getAssessmentVoidedDate() != null)
                                        .build()))
                .collect(Collectors.toList()));
    }

}

