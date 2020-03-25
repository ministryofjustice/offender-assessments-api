package uk.gov.justice.digital.oasys.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleAssessmentRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AssessmentsServiceTest {

    @Mock
    SimpleAssessmentRepository assessmentRepository;

    @Mock
    OffenderService offenderService;

    @Mock
    SectionService sectionService;

    @Mock
    Assessment assessment;

    @Mock
    AssessmentGroup assessmentGroup;

    @Mock
    RefAssessmentVersion version;

    AssessmentsService assessmentsService;

    @BeforeEach
    public void setUp(){
        assessmentsService = new AssessmentsService(assessmentRepository, offenderService, sectionService);
        setupAssessmentGroup();
        setupVersion();
        setupAssessment();
    }

    @Test
    public void shouldGetAssessment() {
        Long oasysId = 21345L;
        when(assessmentRepository.getAssessment(oasysId)).thenReturn(Optional.of(assessment));

        assessmentsService.getAssessment(oasysId);

        verify(assessmentRepository, times(1)).getAssessment(oasysId);
        verifyNoInteractions(offenderService);

    }

    @Test
    public void shouldGetAssessmentNull() {
        Long oasysId = 21345L;
        when(assessmentRepository.getAssessment(oasysId)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->  assessmentsService.getAssessment(oasysId))
                .isInstanceOf(ApplicationExceptions.EntityNotFoundException.class);


    }

    @Test
    public void shouldGetAssessmentForOffender() {
        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;

        when(offenderService.getOffenderIdByIdentifier(offenderOasys, offenderIdentifier.toString())).thenReturn(oasysId);
        when(assessmentRepository.getAssessmentsForOffender(oasysId, null, null, null, null)).thenReturn(Set.of(assessment));

        assessmentsService.getAssessmentsForOffender(offenderOasys, offenderIdentifier.toString(), null, null, null, null);

        verify(offenderService, times(1)).getOffenderIdByIdentifier(offenderOasys, offenderIdentifier.toString());
        verify(assessmentRepository, times(1)).getAssessmentsForOffender(oasysId, null, null, null, null);
        verifyNoMoreInteractions(offenderService);
        verifyNoMoreInteractions(assessmentRepository);

    }

    @Test
    public void shouldGetAssessmentForOffenderAssessmentEmpty() {
        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;

        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getAssessmentsForOffender(oasysId, null, null, null, null)).thenReturn(Set.of());

        assessmentsService.getAssessmentsForOffender(offenderIdentifier.toString(), offenderOasys,null, null, null, null);

        verify(offenderService, times(1)).getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys);
        verify(assessmentRepository, times(1)).getAssessmentsForOffender(oasysId, null, null, null, null);
        verifyNoMoreInteractions(offenderService);
        verifyNoMoreInteractions(assessmentRepository);
    }

    @Test
    public void shouldGetLatestAssessment() {
        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;

        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        verify(offenderService, times(1)).getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys);
        verify(assessmentRepository, times(1)).getLatestAssessment(oasysId, null, null, null, null);
        verifyNoMoreInteractions(offenderService);
        verifyNoMoreInteractions(assessmentRepository);
    }

    @Test()
    public void shouldGetLatestAssessmentNull() {
        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;

        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.empty());

        assertThatThrownBy(() ->  assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null))
                .isInstanceOf(ApplicationExceptions.EntityNotFoundException.class);

    }

    private void setupAssessmentGroup() {
        when(assessmentGroup.getHistoricStatus()).thenReturn("Any HistoricStatus");
    }

    private void setupVersion() {
        when(version.getRefAssVersionUk()).thenReturn(1L);
        when(version.getVersionNumber()).thenReturn("Any Version");
        when(version.getRefAssVersionCode()).thenReturn("Any Ref Version Code");
        when(version.getOasysScoringAlgVersion()).thenReturn(2L);
    }

    private void setupAssessment() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime completed = created.plusMonths(3);
        LocalDateTime voided = created.plusMonths(4);

        when(assessment.getOasysSetPk()).thenReturn(1234L);
        when(assessment.getAssessorName()).thenReturn("Any Name");
        when(assessment.getAssessmentStatus()).thenReturn("STATUS");
        when(assessment.getAssessmentType()).thenReturn("LAYER_3");
        when(assessment.getCreateDate()).thenReturn(created);
        when(assessment.getDateCompleted()).thenReturn(completed);
        when(assessment.getAssessmentVoidedDate()).thenReturn(voided);
        when(assessment.getAssessmentVersion()).thenReturn(version);
        when(assessment.getOasysSections()).thenReturn(Set.of());
        //when(assessment.getOasysBcsParts()).thenReturn(Set.of());
        when(assessment.getGroup()).thenReturn(assessmentGroup);
    }

}