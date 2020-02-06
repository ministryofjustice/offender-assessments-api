package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.api.Sentence;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.digital.oasys.controller.ControllerTestContext.anOffender;

public class OffenderEntityTransformerTest {

    @Test
    public void offenderHasSentenceDetail() {
        var offender = anOffender().get();

        var expected = Sentence.builder()
                .activity("activity")
                .cja(true)
                .cjaSupervisionMonths(10L)
                .cjaUnpaidHours(10L)
                .custodial(true)
                .endDate(Timestamp.from(Instant.MAX).toLocalDateTime().toLocalDate().toString())
                .orderType("orderType")
                .sentenceCode("sentenceCode")
                .sentenceDescription("sentenceDesc")
                .startDate(Timestamp.from(Instant.MIN).toLocalDateTime().toLocalDate().toString())
                .parolable(false)
                .build();

        assertThat(Offender.from(offender).getSentence().toArray()[0]).isEqualTo(expected);
    }

    @Test
    public void offenderHasParoleSentenceType() {
        var offender = anOffender().get();

        var expected = Sentence.builder()
                .activity("another activity")
                .cja(true)
                .cjaSupervisionMonths(5L)
                .cjaUnpaidHours(5L)
                .custodial(true)
                .endDate(Timestamp.from(Instant.MAX).toLocalDateTime().toLocalDate().toString())
                .orderType("orderType")
                .sentenceCode("310")
                .sentenceDescription("Life")
                .startDate(Timestamp.from(Instant.MIN).toLocalDateTime().toLocalDate().toString())
                .parolable(true)
                .build();

        assertThat(Offender.from(offender).getSentence().toArray()[1]).isEqualTo(expected);
    }
}