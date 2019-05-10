package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.oasys.jpa.entity.CmsStubOffender;

import java.util.Optional;

public interface CmsStubOffenderRepository extends JpaRepository<CmsStubOffender, Long> {

    Optional<CmsStubOffender> findByCmsProbNumber(String crn);

}
