package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class SentenceDtoTest {

    @Test
    public void shouldTransformValidOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockValid());
        var firstResult = result.stream().findFirst().get();

        assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        assertThat(firstResult.getCustodial()).isTrue();
        assertThat(firstResult.getCja()).isTrue();
        assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        assertThat(firstResult.getOrderType().getCode()).isEqualTo("typeCode");
        assertThat(firstResult.getOrderType().getDescription()).isEqualTo("orderType");
        assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        assertThat(firstResult.getActivity()).isEqualTo("activity");
        assertThat(firstResult.getOffenceBlockType().getCode()).isEqualTo("typeCode");
        assertThat(firstResult.getOffenceBlockType().getDescription()).isEqualTo("offenceType");
    }

    @Test
    public void shouldTransformNullDetailOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockNullDetail());
        var firstResult = result.stream().findFirst().get();

        assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        assertThat(firstResult.getCustodial()).isTrue();
        assertThat(firstResult.getCja()).isTrue();
        assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        assertThat(firstResult.getOrderType().getCode()).isEqualTo("typeCode");
        assertThat(firstResult.getOrderType().getDescription()).isEqualTo("orderType");
        assertThat(firstResult.getCjaSupervisionMonths()).isNull();
        assertThat(firstResult.getCjaUnpaidHours()).isNull();
        assertThat(firstResult.getActivity()).isNull();
        assertThat(firstResult.getOffenceBlockType().getCode()).isEqualTo("typeCode");
        assertThat(firstResult.getOffenceBlockType().getDescription()).isEqualTo("offenceType");
    }

    @Test
    public void shouldTransformNullSentenceOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockNullSentence());
        var firstResult = result.stream().findFirst().get();

        assertThat(firstResult.getSentenceCode()).isNull();
        assertThat(firstResult.getSentenceDescription()).isNull();
        assertThat(firstResult.getCustodial()).isNull();
        assertThat(firstResult.getCja()).isNull();
        assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        assertThat(firstResult.getOrderType()).isNull();
        assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        assertThat(firstResult.getActivity()).isEqualTo("activity");
    }

    private static Set<OffenceBlock> anOffenceBlockValid() {
        return Set.of(OffenceBlock.builder()
                        .offenceBlockPk(1L)
                        .offenceBlockType(RefElement.builder().refElementCode("typeCode").refElementDesc("offenceType").build())
                        .sentenceDate(LocalDate.MAX)
                        .offenceDate(LocalDate.MIN)
                        .sentLengthCustDays(100l)
                        .offenceSentenceDetail(OffenceSentenceDetail
                                .builder()
                                .activityDesc("activity")
                                .cjaSupervisionMonths(15L)
                                .cjaUnpaidHours(10L)
                                .build())
                        .sentence(Sentence
                                .builder()
                                .cjaInd("Y")
                                .custodialInd("Y")
                                .orderType(RefElement.builder().refElementCode("typeCode").refElementDesc("orderType").build())
                                .sentenceCode("sentenceCode")
                                .sentenceDesc("sentenceDesc")
                                .build()
                        )
                        .build());
    }

    private static Set<OffenceBlock> anOffenceBlockNullDetail() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .offenceBlockType(RefElement.builder().refElementCode("typeCode").refElementDesc("offenceType").build())
                .offenceSentenceDetail(null)
                .sentenceDate(LocalDate.MAX)
                .offenceDate(LocalDate.MIN)
                .sentLengthCustDays(100l)
                .sentence(Sentence
                        .builder()
                        .cjaInd("Y")
                        .custodialInd("Y")
                        .orderType(RefElement.builder().refElementCode("typeCode").refElementDesc("orderType").build())
                        .sentenceCode("sentenceCode")
                        .sentenceDesc("sentenceDesc")
                        .build()
                )
                .build());
    }

    private static Set<OffenceBlock> anOffenceBlockNullSentence() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .sentenceDate(LocalDate.MAX)
                .offenceDate(LocalDate.MIN)
                .sentLengthCustDays(100l)
                .offenceSentenceDetail(OffenceSentenceDetail
                        .builder()
                        .activityDesc("activity")
                        .cjaSupervisionMonths(15L)
                        .cjaUnpaidHours(10L)
                        .build())
                .sentence(null)
                .build());
    }

}