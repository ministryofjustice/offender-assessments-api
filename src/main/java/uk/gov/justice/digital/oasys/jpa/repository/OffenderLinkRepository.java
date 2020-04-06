package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderLink;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OffenderLinkRepository extends JpaRepository<OffenderLink, Long> {

    @Query("SELECT o FROM OffenderLink o where (o.decidingOffenderPK = ?1 OR o.initiatingOffenderPK = ?1) " +
            "AND (o.linkType.refElementCode = 'MERGE_RETAIN' OR o.linkType.refElementCode = 'MERGE_RELINQUISH') " +
            "AND o.mergedOffenderPK IS NOT NULL")
    Optional<OffenderLink> findMergedOffender(Long offenderPK);

}
