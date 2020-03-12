package uk.gov.justice.digital.oasys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleSectionRepository;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.Collection;

import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.utils.LogEvent.EVENT;

@Slf4j
@Service
@Transactional(readOnly = true)
public class SectionService {

    private final SimpleSectionRepository simpleSectionRepository;

    @Autowired
    public SectionService(SimpleSectionRepository simpleSectionRepository) {
        this.simpleSectionRepository = simpleSectionRepository;
    }


    public Section getSectionForAssessment(Long oasysSetId, String sectionId) {
        var section = simpleSectionRepository.getSectionForAssessment(oasysSetId,sectionId);
        log.info("Found Section id: {} for oasysSetId: {}", sectionId, oasysSetId, value(EVENT, LogEvent.GET_SECTION_FOUND));
        return section;
    }

    public Collection<Section> getSectionsForAssessment(Long oasysSetId, String... sectionIds) {
        var sections = simpleSectionRepository.getSectionsForAssessment(oasysSetId, sectionIds);
        log.info("Found {} Sections for oasysSetId: {}", sections.size(), oasysSetId, value(EVENT, LogEvent.GET_SECTIONS_FOUND));
        return sections;
    }

}
