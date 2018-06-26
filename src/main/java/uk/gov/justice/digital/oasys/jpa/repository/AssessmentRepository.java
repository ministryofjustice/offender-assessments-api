package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

@Repository
public interface AssessmentRepository extends JpaRepository<OasysSet, Long> {
}
