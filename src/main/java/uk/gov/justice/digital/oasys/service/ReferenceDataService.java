package uk.gov.justice.digital.oasys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.RefElementDto;
import uk.gov.justice.digital.oasys.jpa.repository.ReferenceDataRepository;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReferenceDataService {

    private ReferenceDataRepository referenceDataRepository;

    public ReferenceDataService(ReferenceDataRepository referenceDataRepository) {
        this.referenceDataRepository = referenceDataRepository;
    }

    public List<RefElementDto> getActiveReferenceDataOfCategory(String categoryCode) {
        log.info("Retrieving reference data of category: {}", categoryCode, LogEvent.GET_REF_DATA_BY_CATEGORY);
        var referenceData = referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(categoryCode, LocalDateTime.now());
        log.info("Found {} reference data of category: {}", referenceData.size(), categoryCode, LogEvent.GET_REF_DATA_BY_CATEGORY);
        return referenceData.stream().map(RefElementDto::from).collect(Collectors.toList());
    }
}
