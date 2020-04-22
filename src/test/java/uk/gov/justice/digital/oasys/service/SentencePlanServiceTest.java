package uk.gov.justice.digital.oasys.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleAssessmentRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SentencePlanServiceTest {

    @Mock
    SimpleAssessmentRepository simpleAssessmentRepository;

    @Mock
    SectionService sectionService;

    @Mock
    OffenderService offenderService;


    SentencePlanService service;

    @BeforeEach
    public void setup() {
        when(offenderService.getOffenderIdByIdentifier("OASYS", "1")).thenReturn(1L);
        service = new SentencePlanService(offenderService, simpleAssessmentRepository, sectionService);

    }

    @Test
    void getFullSentencePlansForOffenderShouldIgnoreNullObjects() {

        var assessments = Set.of(
                Assessment.builder()
                        .createDate(LocalDateTime.of(2020, 3, 3, 9, 0))
                        .oasysSetPk(1L)
                        .oasysSections(Collections.emptySet())
                        .sspObjectivesInSets(Collections.emptySet()).build(),
                Assessment.builder()
                        .createDate(LocalDateTime.of(2021, 3, 3, 9, 0))
                        .oasysSetPk(2L)
                        .oasysSections(Collections.emptySet())
                        .sspObjectivesInSets(Collections.emptySet()).build());

        when(simpleAssessmentRepository.getAssessmentsForOffender(1L,
                null,
                null,
                null,
                null)).thenReturn(assessments);

        //no Sentence Plan section for Oasys Set 1
        when(sectionService.getSectionsForAssessment(1L, "ISP", "RSP")).thenReturn(Collections.emptySet());

        //Oasys Set 2 has a sentence plan section
        when(sectionService.getSectionsForAssessment(2L, "ISP", "RSP")).thenReturn(
                Set.of(Section.builder().oasysQuestions(Collections.emptySet()).build()));


        var result = service.getFullSentencePlansForOffender("OASYS", "1", null, null, null, null);
        assertThat(result).hasSize(1);
        assertThat(result).extracting("oasysSetId").containsOnly(2L);
    }

    @Test
    void getSummarySentencePlansForOffenderShouldIgnoreNullObjects() {
        var assessments = Set.of(
                Assessment.builder()
                        .createDate(LocalDateTime.of(2020, 3, 3, 9, 0))
                        .oasysSetPk(1L)
                        .oasysSections(Collections.emptySet())
                        .sspObjectivesInSets(Collections.emptySet()).build(),
                Assessment.builder()
                        .createDate(LocalDateTime.of(2021, 3, 3, 9, 0))
                        .oasysSetPk(2L)
                        .oasysSections(Collections.emptySet())
                        .sspObjectivesInSets(Collections.emptySet()).build());

        when(simpleAssessmentRepository.getAssessmentsForOffender(1L,
                null,
                null,
                null,
                null)).thenReturn(assessments);

        //no Sentence Plan section for Oasys Set 1
        when(sectionService.getSectionsForAssessment(1L, "ISP", "RSP")).thenReturn(Collections.emptySet());

        //Oasys Set 2 has a sentence plan section
        when(sectionService.getSectionsForAssessment(2L, "ISP", "RSP")).thenReturn(
                Set.of(Section.builder().oasysQuestions(Collections.emptySet()).build()));


        var result = service.getFullSentencePlanSummariesForOffender("OASYS", "1", null, null, null, null);
        assertThat(result).hasSize(1);
        assertThat(result).extracting("oasysSetId").containsOnly(2L);

    }
}