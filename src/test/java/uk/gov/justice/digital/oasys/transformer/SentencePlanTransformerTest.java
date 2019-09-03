package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjective;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class SentencePlanTransformerTest {

    @Test
    public void sentencePlanHasCreatedDate() {
        var today = LocalDateTime.now();

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(today))
                .sspObjectivesInSets(List.of(SspObjectivesInSet.builder().createDate(Timestamp.valueOf(today)).build()))
                .build();

        final SentencePlanTransformer sentencePlanTransformer = new SentencePlanTransformer();
        assertThat(sentencePlanTransformer.sentencePlanOf(oasysSet)).isPresent();
        final ProperSentencePlan actual = sentencePlanTransformer.sentencePlanOf(oasysSet).get();

        assertThat(actual.getCreatedDate()).isEqualTo(today.toLocalDate());
    }

    @Test
    public void sentencePlanHasCompletedDateIfPresent() {
        var today = LocalDateTime.now();
        var threeMonthsFromNow = LocalDateTime.now().plusMonths(30);

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(today))
                .dateCompleted(Timestamp.valueOf(threeMonthsFromNow))
                .sspObjectivesInSets(List.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(Timestamp.valueOf(today)).build()).build()))
                .build();

        final SentencePlanTransformer sentencePlanTransformer = new SentencePlanTransformer();
        assertThat(sentencePlanTransformer.sentencePlanOf(oasysSet)).isPresent();
        final ProperSentencePlan actual = sentencePlanTransformer.sentencePlanOf(oasysSet).get();

        assertThat(actual.getCompletedDate()).isEqualTo(threeMonthsFromNow.toLocalDate());
    }

    @Test
    public void sentencePlanHasNullCompletedDateIfNotPresent() {
        var today = LocalDateTime.now();


        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(today))
                .dateCompleted(null)
                .sspObjectivesInSets(List.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(Timestamp.valueOf(today)).build()).build()))
                .build();

        final SentencePlanTransformer sentencePlanTransformer = new SentencePlanTransformer();
        assertThat(sentencePlanTransformer.sentencePlanOf(oasysSet)).isPresent();
        final ProperSentencePlan actual = sentencePlanTransformer.sentencePlanOf(oasysSet).get();

        assertThat(actual.getCompletedDate()).isNull();
    }

}