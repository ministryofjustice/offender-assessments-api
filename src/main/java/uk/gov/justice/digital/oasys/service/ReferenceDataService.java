package uk.gov.justice.digital.oasys.service;

import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.RefElementDto;
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

    public List<RefElementDto> getActiveReferenceDataOfCategory(String categoryCode) {
        var referenceData = referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(categoryCode, LocalDateTime.now());
        return referenceData.stream().map(RefElementDto::from).collect(Collectors.toList());
    }
}
