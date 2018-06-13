package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

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
                .flatMap(oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets().stream())
                .map(oasysSet -> getBuild(oasysSet))
                .collect(Collectors.toList()));
    }

    private Ogrs getBuild(OasysSet oasysSet) {
        return Ogrs.builder()
                .oasysSetId(oasysSet.getOasysSetPk())
                .ogrs3_1_year(oasysSet.getOgrs31Year())
                .ogrs3_2_year(oasysSet.getOgrs32Year())
                .build();
    }

}
