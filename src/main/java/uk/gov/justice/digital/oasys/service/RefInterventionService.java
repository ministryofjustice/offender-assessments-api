package uk.gov.justice.digital.oasys.service;

import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.ReferenceIntervention;
import uk.gov.justice.digital.oasys.jpa.repository.ReferenceDataRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefInterventionService {

    private ReferenceDataRepository referenceDataRepository;
    private final static String INTERVENTION_CAT_CODE = "INTERVENTION";

    public RefInterventionService(ReferenceDataRepository referenceDataRepository) {
        this.referenceDataRepository = referenceDataRepository;
    }

    public List<ReferenceIntervention> getAllInterventions() {
        var interventions = referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(INTERVENTION_CAT_CODE, LocalDateTime.now());

        return interventions.stream().map(r->ReferenceIntervention.from(r)).collect(Collectors.toList());
    }
}
