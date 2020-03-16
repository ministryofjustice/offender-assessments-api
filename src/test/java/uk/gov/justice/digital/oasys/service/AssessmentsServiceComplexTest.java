package uk.gov.justice.digital.oasys.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleAssessmentRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentsServiceComplexTest {

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

    @Before
    public void setUp(){
        assessmentsService = new AssessmentsService(assessmentRepository, offenderService, sectionService);
        setupAssessmentGroup();
        setupVersion();
    }

    @Test
    public void shouldGetAssessmentNoChildSafeGuardingNotLAYER3() {
        setupAssessment("LAYER_2");

        Long oasysId = 21345L;
        when(assessmentRepository.getAssessment(oasysId)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getAssessment(oasysId);

        assertThat(assessmentDto.getChildSafeguardingIndicated()).isNull();
    }

    @Test
    public void shouldGetLatestAssessmentNoChildSafeGuardingNotLAYER3() {
        setupAssessment("LAYER_2");

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getChildSafeguardingIndicated()).isNull();
    }

    @Test
    public void shouldGetLatestAssessmentValidROSHPositive() {
        setupAssessment("LAYER_3");
        var section = Mockito.mock(Section.class);

        when(section.getRefAnswers(any())).thenReturn(Map.of("R2.1", "Y"));
        when(sectionService.getSectionForAssessment(1234L,"ROSH")).thenReturn(section);

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getChildSafeguardingIndicated()).isTrue();
    }

    @Test
    public void shouldGetLatestAssessmentValidROSHNegative() {
        setupAssessment("LAYER_3");
        var section = Mockito.mock(Section.class);

        when(section.getRefAnswers(any())).thenReturn(Map.of("R2.1", "N"));
        when(sectionService.getSectionForAssessment(1234L,"ROSH")).thenReturn(section);

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getChildSafeguardingIndicated()).isFalse();
    }

    @Test
    public void shouldGetLatestAssessmentValidROSHEmpty() {
        setupAssessment("LAYER_3");
        var section = Mockito.mock(Section.class);

        when(section.getRefAnswers(any())).thenReturn(Map.of());
        when(sectionService.getSectionForAssessment(1234L,"ROSH")).thenReturn(section);

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);


        assertThat(assessmentDto.getChildSafeguardingIndicated()).isNull();
    }

    @Test
    public void shouldGetLatestAssessmentValidROSHNullSection() {
        setupAssessment("LAYER_3");
        when(sectionService.getSectionForAssessment(1234L,"ROSH")).thenReturn(null);

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getChildSafeguardingIndicated()).isNull();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsEmpty() {
        setupAssessment("LAYER_3");

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of());

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).isEmpty();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidPositiveHarmOnly() {
        setupAssessment("LAYER_3");

        var section = Mockito.mock(Section.class);
        var refSection = Mockito.mock(RefSection.class);

        when(section.getSectOtherRawScore()).thenReturn(10L);
        when(section.getRefSection()).thenReturn(refSection);
        when(section.hasRefSection()).thenReturn(true);
        when(section.getRefAnswers(any())).thenReturn(Map.of("3.98", "Yes"));

        when(refSection.getRefSectionCode()).thenReturn("3");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(1);

        var needDto = assessmentDto.getLayer3SentencePlanNeeds().stream().findFirst().get();
        assertThat(needDto.getRiskOfHarm()).isTrue();
        assertThat(needDto.getRiskOfReoffending()).isFalse();
        assertThat(needDto.getFlaggedAsNeed()).isFalse();
        assertThat(needDto.getOverThreshold()).isFalse();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidPositiveReoffendingOnly() {
        setupAssessment("LAYER_3");

        var section = Mockito.mock(Section.class);
        var refSection = Mockito.mock(RefSection.class);

        when(section.getSectOtherRawScore()).thenReturn(10L);
        when(section.getRefSection()).thenReturn(refSection);
        when(section.hasRefSection()).thenReturn(true);
        when(section.getRefAnswers(any())).thenReturn(Map.of("3.99", "Yes"));

        when(refSection.getRefSectionCode()).thenReturn("3");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(1);

        var needDto = assessmentDto.getLayer3SentencePlanNeeds().stream().findFirst().get();
        assertThat(needDto.getRiskOfHarm()).isFalse();
        assertThat(needDto.getRiskOfReoffending()).isTrue();
        assertThat(needDto.getFlaggedAsNeed()).isFalse();
        assertThat(needDto.getOverThreshold()).isFalse();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidPositiveFlaggedAsNeed() {
        setupAssessment("LAYER_3");

        var section = Mockito.mock(Section.class);
        var refSection = Mockito.mock(RefSection.class);

        when(section.getSectOtherRawScore()).thenReturn(10L);
        when(section.getRefSection()).thenReturn(refSection);
        when(section.hasRefSection()).thenReturn(true);
        when(section.getRefAnswers(any())).thenReturn(Map.of());
        when(section.getLowScoreNeedAttnInd()).thenReturn("YES");

        when(refSection.getRefSectionCode()).thenReturn("3");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(1);

        var needDto = assessmentDto.getLayer3SentencePlanNeeds().stream().findFirst().get();
        assertThat(needDto.getRiskOfHarm()).isFalse();
        assertThat(needDto.getRiskOfReoffending()).isFalse();
        assertThat(needDto.getFlaggedAsNeed()).isTrue();
        assertThat(needDto.getOverThreshold()).isFalse();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidPositiveOverThreshHold() {
        setupAssessment("LAYER_3");

        var section = Mockito.mock(Section.class);
        var refSection = Mockito.mock(RefSection.class);

        when(section.getSectOtherRawScore()).thenReturn(30L);
        when(section.getRefSection()).thenReturn(refSection);
        when(section.hasRefSection()).thenReturn(true);
        when(section.getRefAnswers(any())).thenReturn(Map.of());
        when(section.getLowScoreNeedAttnInd()).thenReturn("No");

        when(refSection.getRefSectionCode()).thenReturn("3");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(1);

        var needDto = assessmentDto.getLayer3SentencePlanNeeds().stream().findFirst().get();
        assertThat(needDto.getRiskOfHarm()).isFalse();
        assertThat(needDto.getRiskOfReoffending()).isFalse();
        assertThat(needDto.getFlaggedAsNeed()).isFalse();
        assertThat(needDto.getOverThreshold()).isTrue();
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidPositiveMultiple() {
        setupAssessment("LAYER_3");

        var section1 = Mockito.mock(Section.class);
        var refSection1 = Mockito.mock(RefSection.class);
        var section2 = Mockito.mock(Section.class);
        var refSection2 = Mockito.mock(RefSection.class);

        when(section1.getSectOtherRawScore()).thenReturn(10L);
        when(section1.getRefSection()).thenReturn(refSection1);
        when(section1.hasRefSection()).thenReturn(true);
        when(section1.getRefAnswers(any())).thenReturn(Map.of("10.98", "Yes"));

        when(refSection1.getRefSectionCode()).thenReturn("10");
        when(refSection1.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(section2.getSectOtherRawScore()).thenReturn(10L);
        when(section2.getRefSection()).thenReturn(refSection2);
        when(section2.hasRefSection()).thenReturn(true);
        when(section2.getRefAnswers(any())).thenReturn(Map.of("3.98", "No"));

        when(refSection2.getRefSectionCode()).thenReturn("3");
        when(refSection2.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section1,section2));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(1);
    }

    @Test
    public void shouldGetLatestAssessmentNeedsValidNegative() {
        setupAssessment("LAYER_3");

        var section = Mockito.mock(Section.class);
        var refSection = Mockito.mock(RefSection.class);

        when(section.getSectOtherRawScore()).thenReturn(10L);
        when(section.getRefSection()).thenReturn(refSection);
        when(section.hasRefSection()).thenReturn(true);
        when(section.getRefAnswers(any())).thenReturn(Map.of("10.98", "No"));


        when(refSection.getRefSectionCode()).thenReturn("10");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(20L);

        when(sectionService.getSectionsForAssessment(eq(1234L),any())).thenReturn(Set.of(section));

        Long oasysId = 21345L;
        String offenderOasys = oasysId.toString();
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.OASYS;
        when(offenderService.getOffenderIdByIdentifier(offenderIdentifier.toString(), offenderOasys)).thenReturn(oasysId);
        when(assessmentRepository.getLatestAssessment(oasysId, null, null, null, null)).thenReturn(Optional.of(assessment));

        var assessmentDto = assessmentsService.getLatestAssessmentForOffender(offenderIdentifier.toString(), offenderOasys, null, null, null, null);

        assertThat(assessmentDto.getLayer3SentencePlanNeeds()).hasSize(0);
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

    private void setupAssessment(String layer) {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime completed = created.plusMonths(3);
        LocalDateTime voided = created.plusMonths(4);

        when(assessment.getOasysSetPk()).thenReturn(1234L);
        when(assessment.getAssessorName()).thenReturn("Any Name");
        when(assessment.getAssessmentStatus()).thenReturn("STATUS");
        when(assessment.getAssessmentType()).thenReturn(layer);
        when(assessment.getCreateDate()).thenReturn(created);
        when(assessment.getDateCompleted()).thenReturn(completed);
        when(assessment.getAssessmentVoidedDate()).thenReturn(voided);
        when(assessment.getAssessmentVersion()).thenReturn(version);
        when(assessment.getOasysSections()).thenReturn(Set.of());
        //when(assessment.getOasysBcsParts()).thenReturn(Set.of());
        when(assessment.getGroup()).thenReturn(assessmentGroup);
    }

}