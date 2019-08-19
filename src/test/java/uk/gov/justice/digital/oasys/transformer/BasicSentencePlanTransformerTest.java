package uk.gov.justice.digital.oasys.transformer;

import org.junit.Test;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicSentencePlanTransformerTest {

    @Test
    public void sentencePlanHasCreatedDateAndOasysSetId() {
        var today = LocalDate.now();
        var now = Instant.now();

        var oasysSet = OasysSet.builder()
                .createDate(Timestamp.from(now))
                .oasysSetPk(12345L)
                .basicSentencePlanList(Collections.emptyList())
                .build();

        final BasicSentencePlanTransformer sentencePlanTransformer = new BasicSentencePlanTransformer();
        assertThat(sentencePlanTransformer.basicSentencePlanOf(oasysSet)).isPresent();
        final BasicSentencePlan actual = sentencePlanTransformer.basicSentencePlanOf(oasysSet).get();

        assertThat(actual.getCreatedDate()).isEqualTo(today);
        assertThat(actual.getSentencePlanId()).isEqualTo(12345L);
    }
}