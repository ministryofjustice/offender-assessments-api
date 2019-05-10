package uk.gov.justice.digital.oasys.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;

@Repository
public interface SentencePlanRepository extends JpaRepository<BasicSentencePlanObj, Long> {
}
