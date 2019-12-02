package uk.gov.justice.digital.oasys.service;

import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.ReferenceDataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenceDataService {

    private ReferenceDataRepository referenceDataRepository;

    public ReferenceDataService(ReferenceDataRepository referenceDataRepository) {
        this.referenceDataRepository = referenceDataRepository;
    }

    public List<RefElement> getActiveReferenceDataOfCategory(String categoryCode) {
        var interventions = referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(categoryCode, LocalDateTime.now());
        return interventions.stream().map(r->RefElement.from(r)).collect(Collectors.toList());
    }
}
