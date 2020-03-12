package uk.gov.justice.digital.oasys.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import uk.gov.justice.digital.oasys.jpa.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public class SentenceDtoTest {

    @Test
    public void shouldTransformValidOffenceBlock() {

        var result = SentenceDto.from(anAssessmentGroupValid());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        Assertions.assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        Assertions.assertThat(firstResult.getCustodial()).isTrue();
        Assertions.assertThat(firstResult.getCja()).isTrue();
        Assertions.assertThat(firstResult.getStartDate()).isEqualTo(LocalDate.MIN);
        Assertions.assertThat(firstResult.getEndDate()).isEqualTo(LocalDate.MAX);
        Assertions.assertThat(firstResult.getOrderType()).isEqualTo("orderType");
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        Assertions.assertThat(firstResult.getActivity()).isEqualTo("activity");
        Assertions.assertThat(firstResult.getParolable()).isFalse();
    }

    @Test
    public void shouldTransformNullDetailOffenceBlock() {

        var result = SentenceDto.from(anAssessmentGroupNullDetails());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isEqualTo("sentenceCode");
        Assertions.assertThat(firstResult.getSentenceDescription()).isEqualTo("sentenceDesc");
        Assertions.assertThat(firstResult.getCustodial()).isTrue();
        Assertions.assertThat(firstResult.getCja()).isTrue();
        Assertions.assertThat(firstResult.getStartDate()).isEqualTo(LocalDate.MIN);
        Assertions.assertThat(firstResult.getEndDate()).isEqualTo(LocalDate.MAX);
        Assertions.assertThat(firstResult.getOrderType()).isEqualTo("orderType");
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isNull();
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isNull();
        Assertions.assertThat(firstResult.getActivity()).isNull();
        Assertions.assertThat(firstResult.getParolable()).isFalse();
    }

    @Test
    public void shouldTransformNullSentenceOffenceBlock() {

        var result = SentenceDto.from(anAssessmentGroupNullSentence());
        var firstResult = result.stream().findFirst().get();

        Assertions.assertThat(firstResult.getSentenceCode()).isNull();
        Assertions.assertThat(firstResult.getSentenceDescription()).isNull();
        Assertions.assertThat(firstResult.getCustodial()).isNull();
        Assertions.assertThat(firstResult.getCja()).isNull();
        Assertions.assertThat(firstResult.getStartDate()).isNull();
        Assertions.assertThat(firstResult.getEndDate()).isNull();
        Assertions.assertThat(firstResult.getOrderType()).isNull();
        Assertions.assertThat(firstResult.getCjaSupervisionMonths()).isEqualTo(15L);
        Assertions.assertThat(firstResult.getCjaUnpaidHours()).isEqualTo(10L);
        Assertions.assertThat(firstResult.getActivity()).isEqualTo("activity");
        Assertions.assertThat(firstResult.getParolable()).isNull();
    }

    private static List<OasysAssessmentGroup> anAssessmentGroupValid() {
        return List.of(OasysAssessmentGroup.builder().oasysSets(anOasysSetValid()).build());
    }

    private static List<OasysAssessmentGroup> anAssessmentGroupNullDetails() {
        return List.of(OasysAssessmentGroup.builder().oasysSets(anOasysSetNullDetails()).build());
    }

    private static List<OasysAssessmentGroup> anAssessmentGroupNullSentence() {
        return List.of(OasysAssessmentGroup.builder().oasysSets(anOasysSetNullSentence()).build());
    }

    private static List<OasysSet> anOasysSetValid() {
        return List.of(OasysSet.builder()
                .offenceBlock(anOffenceBlockValid())
                .createDate(LocalDateTime.now())
                .build());
    }

    private static List<OasysSet> anOasysSetNullDetails() {
        return List.of(OasysSet.builder()
                .offenceBlock(anOffenceBlockNullDetail())
                .createDate(LocalDateTime.now())
                .build());
    }

    private static List<OasysSet> anOasysSetNullSentence() {
        return List.of(OasysSet.builder()
                .offenceBlock(anOffenceBlockNullSentence())
                .createDate(LocalDateTime.now())
                .build());
    }

    private static Set<OffenceBlock> anOffenceBlockValid() {
        return Set.of(OffenceBlock.builder()
                        .offenceBlockPk(1L)
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
                                .endDate(LocalDate.MAX)
                                .orderType(uk.gov.justice.digital.oasys.jpa.entity.RefElement.builder().refElementDesc("orderType").build())
                                .sentenceCode("sentenceCode")
                                .sentenceDesc("sentenceDesc")
                                .startDate(LocalDate.MIN)
                                .build()
                        )
                        .build());
    }

    private static Set<OffenceBlock> anOffenceBlockNullDetail() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .offenceSentenceDetail(null)
                .sentence(Sentence
                        .builder()
                        .cjaInd("Y")
                        .custodialInd("Y")
                        .endDate(LocalDate.MAX)
                        .orderType(uk.gov.justice.digital.oasys.jpa.entity.RefElement.builder().refElementDesc("orderType").build())
                        .sentenceCode("sentenceCode")
                        .sentenceDesc("sentenceDesc")
                        .startDate(LocalDate.MIN)
                        .build()
                )
                .build());
    }

    private static Set<OffenceBlock> anOffenceBlockNullSentence() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
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