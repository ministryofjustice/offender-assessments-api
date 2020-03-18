package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.RefAssessmentDto;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersionPK;
import uk.gov.justice.digital.oasys.jpa.repository.RefAssessmentRepository;

import java.util.Optional;

@Service
public class RefAssessmentService {
    private final RefAssessmentRepository refAssessmentRepository;

    @Autowired
    public RefAssessmentService(RefAssessmentRepository refAssessmentRepository) {
        this.refAssessmentRepository = refAssessmentRepository;
    }

    public Optional<RefAssessmentDto> getReferenceAssessmentOf(String type, String revision) {

        Optional<RefAssessmentVersion> maybeRefAssVersion = refAssessmentRepository.findById(new RefAssessmentVersionPK(type, revision));

        return maybeRefAssVersion.map(RefAssessmentDto::from);
    }
}