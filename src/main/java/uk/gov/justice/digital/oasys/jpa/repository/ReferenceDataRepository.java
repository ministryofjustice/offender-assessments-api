package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReferenceDataRepository extends JpaRepository<RefElement,Long> {

    List<RefElement> findAllByRefCategoryCodeAndEndDateIsBefore(String refCategoryCode, LocalDateTime endDate);

}
