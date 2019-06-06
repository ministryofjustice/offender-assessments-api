package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SentencePlanTransformerTest {

    @Test
    public void sentencePlanHasCreatedDate() {
        var today = LocalDate.now();
        var now = Instant.now();

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.from(now))
                .basicSentencePlanList(Collections.emptyList())
                .build();

        final SentencePlanTransformer sentencePlanTransformer = new SentencePlanTransformer();
        assertThat(sentencePlanTransformer.sentencePlanOf(oasysSet)).isPresent();
        final BasicSentencePlan actual = sentencePlanTransformer.sentencePlanOf(oasysSet).get();

        assertThat(actual.getCreatedDate()).isEqualTo(today);
    }
}