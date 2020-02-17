package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.ProperSentencePlanDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjective;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class SentencePlanTransformerTest {

    @Test
    public void sentencePlanHasCreatedDate() {
        var today = LocalDate.now();

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(LocalDateTime.MIN))
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().createDate(today).build()))
                .build();

        final ProperSentencePlanDto actual = ProperSentencePlanDto.from(oasysSet);

        assertThat(actual.getCreatedDate()).isEqualTo(today);
    }

    @Test
    public void sentencePlanHasCompletedDateIfPresent() {
        var today = LocalDateTime.now();
        var threeMonthsFromNow = LocalDateTime.now().plusMonths(30);

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(today))
                .dateCompleted(Timestamp.valueOf(threeMonthsFromNow))
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(Timestamp.valueOf(today)).build()).build()))
                .build();

        final ProperSentencePlanDto actual = ProperSentencePlanDto.from(oasysSet);

        assertThat(actual.getCompletedDate()).isEqualTo(threeMonthsFromNow.toLocalDate());
    }

    @Test
    public void sentencePlanHasNullCompletedDateIfNotPresent() {
        var today = LocalDateTime.now();


        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.valueOf(today))
                .dateCompleted(null)
                .sspObjectivesInSets(Set.of(SspObjectivesInSet.builder().sspObjective(SspObjective.builder().createDate(Timestamp.valueOf(today)).build()).build()))
                .build();

        final ProperSentencePlanDto actual = ProperSentencePlanDto.from(oasysSet);

        assertThat(actual.getCompletedDate()).isNull();
    }

}