package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OgrsService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OgrsService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    public Optional <List<Ogrs>> getOgrsForOffenderCRN(String crn){
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        Optional<List<OasysAssessmentGroup>> oasysAssessmentGroups = maybeOffender.map(offender -> offender.getOasysAssessmentGroups());


    }

}
