package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersionPK;

@Repository
public interface RefAssessmentRepository extends JpaRepository<RefAssessmentVersion, RefAssessmentVersionPK> {
}
