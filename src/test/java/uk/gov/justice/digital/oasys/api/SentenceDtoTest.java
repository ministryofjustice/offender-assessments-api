package uk.gov.justice.digital.oasys.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import java.time.LocalDate;
import java.util.Set;


public class SentenceDtoTest {

    @Test
    public void shouldTransformValidOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockValid());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        Assertions.assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        Assertions.assertThat(firstResult.getCustodial()).isTrue();
        Assertions.assertThat(firstResult.getCja()).isTrue();
        Assertions.assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        Assertions.assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        Assertions.assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        Assertions.assertThat(firstResult.getOrderType().getCode()).isEqualTo("typeCode");
        Assertions.assertThat(firstResult.getOrderType().getDescription()).isEqualTo("orderType");
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        Assertions.assertThat(firstResult.getActivity()).isEqualTo("activity");
        Assertions.assertThat(firstResult.getParolable()).isFalse();
        Assertions.assertThat(firstResult.getOffenceBlockType().getCode()).isEqualTo("typeCode");
        Assertions.assertThat(firstResult.getOffenceBlockType().getDescription()).isEqualTo("offenceType");
    }

    @Test
    public void shouldTransformNullDetailOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockNullDetail());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        Assertions.assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        Assertions.assertThat(firstResult.getCustodial()).isTrue();
        Assertions.assertThat(firstResult.getCja()).isTrue();
        Assertions.assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        Assertions.assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        Assertions.assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        Assertions.assertThat(firstResult.getOrderType().getCode()).isEqualTo("typeCode");
        Assertions.assertThat(firstResult.getOrderType().getDescription()).isEqualTo("orderType");
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isNull();
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isNull();
        Assertions.assertThat(firstResult.getActivity()).isNull();
        Assertions.assertThat(firstResult.getParolable()).isFalse();
        Assertions.assertThat(firstResult.getOffenceBlockType().getCode()).isEqualTo("typeCode");
        Assertions.assertThat(firstResult.getOffenceBlockType().getDescription()).isEqualTo("offenceType");
    }

    @Test
    public void shouldTransformNullSentenceOffenceBlock() {

        var result = SentenceDto.from(anOffenceBlockNullSentence());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isNull();
        Assertions.assertThat(firstResult.getSentenceDescription()).isNull();
        Assertions.assertThat(firstResult.getCustodial()).isNull();
        Assertions.assertThat(firstResult.getCja()).isNull();
        Assertions.assertThat(firstResult.getOffenceDate()).isEqualTo(LocalDate.MIN);
        Assertions.assertThat(firstResult.getSentenceDate()).isEqualTo(LocalDate.MAX);
        Assertions.assertThat(firstResult.getSentenceLengthCustodyDays()).isEqualTo(100);
        Assertions.assertThat(firstResult.getOrderType()).isNull();
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        Assertions.assertThat(firstResult.getActivity()).isEqualTo("activity");
        Assertions.assertThat(firstResult.getParolable()).isNull();
    }

    @Test
    public void shouldSetParolableToTrueWhenSentenceCodeIsParolable() {

        var result = SentenceDto.from(anOffenceBlockParolableSentence());
        var firstResult = result.stream().findFirst().get();
        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("310");
        Assertions.assertThat(firstResult.getParolable()).isTrue();
    }

    @Test
    public void shouldSetParolableToFalseWhenSentenceCodeIsNotParolable() {

        var result = SentenceDto.from(anOffenceBlockNotParolableSentence());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("910");
        Assertions.assertThat(firstResult.getParolable()).isFalse();
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

    private static Set<OffenceBlock> anOffenceBlockParolableSentence() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .sentence(Sentence
                        .builder()
                        .sentenceCode("310")
                        .build()
                )
                .build());
    }

    private static Set<OffenceBlock> anOffenceBlockNotParolableSentence() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .sentence(Sentence
                        .builder()
                        .sentenceCode("910")
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