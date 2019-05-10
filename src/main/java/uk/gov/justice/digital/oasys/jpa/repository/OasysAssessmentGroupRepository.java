package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;

@Repository
public interface OasysAssessmentGroupRepository extends JpaRepository<OasysAssessmentGroup, Long> {
}
