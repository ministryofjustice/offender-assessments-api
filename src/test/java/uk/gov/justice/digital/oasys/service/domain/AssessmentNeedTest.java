package uk.gov.justice.digital.oasys.service.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssessmentNeedTest {

    private SectionHeader section = SectionHeader.DRUG_MISUSE;

    @Test
    public void shouldConstructAssessmentNeed() {
        AssessmentNeed assessmentNeed = new AssessmentNeed(
                section,
                null,
                null,
                null,
                null);

        assertThat(assessmentNeed.getSection()).isEqualTo(section);

        assertThat(assessmentNeed.anyRiskFlagged()).isFalse();

    }

    @Test
    public void shouldConstructAssessmentNeedHarm() {
        AssessmentNeed assessmentNeed = new AssessmentNeed(
                section,
                true,
                null,
                null,
                null);

        assertThat(assessmentNeed.getRiskOfHarm()).isTrue();
        assertThat(assessmentNeed.getRiskOfReoffending()).isNull();
        assertThat(assessmentNeed.getOverThreshold()).isNull();
        assertThat(assessmentNeed.getFlaggedAsNeed()).isNull();

        assertThat(assessmentNeed.anyRiskFlagged()).isTrue();

    }

    @Test
    public void shouldConstructAssessmentNeedReoffending() {
        AssessmentNeed assessmentNeed = new AssessmentNeed(
                section,
                null,
                true,
                null,
                null);

        assertThat(assessmentNeed.getRiskOfHarm()).isNull();
        assertThat(assessmentNeed.getRiskOfReoffending()).isTrue();
        assertThat(assessmentNeed.getOverThreshold()).isNull();
        assertThat(assessmentNeed.getFlaggedAsNeed()).isNull();

        assertThat(assessmentNeed.anyRiskFlagged()).isTrue();

    }

    @Test
    public void shouldConstructAssessmentNeedThreshold() {
        AssessmentNeed assessmentNeed = new AssessmentNeed(
                section,
                null,
                null,
                true,
                null);

        assertThat(assessmentNeed.getRiskOfHarm()).isNull();
        assertThat(assessmentNeed.getRiskOfReoffending()).isNull();
        assertThat(assessmentNeed.getOverThreshold()).isTrue();
        assertThat(assessmentNeed.getFlaggedAsNeed()).isNull();

        assertThat(assessmentNeed.anyRiskFlagged()).isTrue();

    }

    @Test
    public void shouldConstructAssessmentNeedFlagged() {
        AssessmentNeed assessmentNeed = new AssessmentNeed(
                section,
                null,
                null,
                null,
                true);

        assertThat(assessmentNeed.getRiskOfHarm()).isNull();
        assertThat(assessmentNeed.getRiskOfReoffending()).isNull();
        assertThat(assessmentNeed.getOverThreshold()).isNull();
        assertThat(assessmentNeed.getFlaggedAsNeed()).isTrue();

        assertThat(assessmentNeed.anyRiskFlagged()).isTrue();

    }
}