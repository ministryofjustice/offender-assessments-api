package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.OffenderDto;
import uk.gov.justice.digital.oasys.api.SentenceDto;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.digital.oasys.controller.ControllerTestContext.anOffender;

public class OffenderDtoTransformerTest {

    @Test
    public void offenderHasSentenceDetail() {
        var offender = anOffender().get();

        var expected = SentenceDto.builder()
                .activity("activity")
                .cja(true)
                .cjaSupervisionMonths(10L)
                .cjaUnpaidHours(10L)
                .custodial(true)
                .endDate(LocalDate.MAX)
                .orderType("orderType")
                .sentenceCode("sentenceCode")
                .sentenceDescription("sentenceDesc")
                .startDate(LocalDate.MIN)
                .parolable(false)
                .build();

        assertThat(OffenderDto.from(offender).getSentence().toArray()[0]).isEqualTo(expected);
    }

    @Test
    public void offenderHasParoleSentenceType() {
        var offender = anOffender().get();

        var expected = SentenceDto.builder()
                .activity("another activity")
                .cja(true)
                .cjaSupervisionMonths(5L)
                .cjaUnpaidHours(5L)
                .custodial(true)
                .endDate(LocalDate.MAX)
                .orderType("orderType")
                .sentenceCode("310")
                .sentenceDescription("Life")
                .startDate(LocalDate.MIN)
                .parolable(true)
                .build();

        assertThat(OffenderDto.from(offender).getSentence().toArray()[1]).isEqualTo(expected);
    }
}