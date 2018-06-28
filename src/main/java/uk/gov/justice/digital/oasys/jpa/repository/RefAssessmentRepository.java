package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersionPK;

@Repository
public interface RefAssessmentRepository extends JpaRepository<RefAssVersion, RefAssVersionPK> {
}
