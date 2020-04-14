package uk.gov.justice.digital.oasys.controller;

import org.mockito.Mockito;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;

public class ControllerTestContext {

    public static void setup(OffenderRepository offenderRepository) {

        Mockito.when(offenderRepository.getByCmsProbNumber(eq("crn1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.getByCmsProbNumber(eq("crn2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.getByCmsPrisNumber(eq("nomisId1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.getByCmsPrisNumber(eq("nomisId2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.getByPrisonNumber(eq("bookingId1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.getByPrisonNumber(eq("bookingId2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.getByPnc(eq("pnc1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.getByPnc(eq("pnc2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.findById(eq(1L))).thenReturn(anOffender());
        Mockito.when(offenderRepository.findById(eq(2L))).thenReturn(Optional.empty());

    }

    public static Optional<Offender> anOffender() {
        return Optional.ofNullable(Offender.builder()
                .oasysAssessmentGroups(anAssessmentGroup())
                .build());
    }

    private static List<OasysAssessmentGroup> anAssessmentGroup() {
        return List.of(OasysAssessmentGroup.builder()
                .oasysAssessmentGroupPk(1L)
                .oasysSets(someOasysSets())
                .build());
    }

    private static List<OasysSet> someOasysSets() {
        return List.of(OasysSet.builder()
                        .createDate(LocalDateTime.now().minusDays(1))
                        .assessmentType(assessmentType("oasys"))
                        .oasysSetPk(1L)
                        .ogrs31Year(BigDecimal.ONE)
                        .ogrs32Year(BigDecimal.TEN)
                        .ogp1Year(BigDecimal.ONE)
                        .ogp2Year(BigDecimal.TEN)
                        .ogpDyWesc(BigDecimal.ONE)
                        .ogpStWesc(BigDecimal.TEN)
                        .ogpTotWesc(BigDecimal.ONE)
                        .ovpStWesc(BigDecimal.TEN)
                        .ovpDyWesc(BigDecimal.ONE)
                        .ovpTotWesc(BigDecimal.TEN)
                        .ovp1Year(BigDecimal.ONE)
                        .ovp2Year(BigDecimal.TEN)
                        .ovpPrevWesc(BigDecimal.ONE)
                        .ovpVioWesc(BigDecimal.TEN)
                        .ovpNonVioWesc(BigDecimal.ONE)
                        .ovpAgeWesc(BigDecimal.TEN)
                        .ovpSexWesc(BigDecimal.ONE)
                        .group(aGroup("HISTORIC"))
                        .assessmentStatus(anAssessmentStatus("OPEN"))
                        .basicSentencePlanList(aSentencePlan(1L))
                        .offenceBlock(anOffenceBlock())
                        .build(),
                OasysSet.builder()
                        .createDate(LocalDateTime.now())
                        .assessmentType(assessmentType("sara"))
                        .oasysSetPk(2L)
                        .ogrs31Year(BigDecimal.TEN)
                        .ogrs32Year(BigDecimal.ONE)
                        .ogp1Year(BigDecimal.TEN)
                        .ogp2Year(BigDecimal.ONE)
                        .ogpDyWesc(BigDecimal.TEN)
                        .ogpStWesc(BigDecimal.ONE)
                        .ogpTotWesc(BigDecimal.TEN)
                        .ovpStWesc(BigDecimal.ONE)
                        .ovpDyWesc(BigDecimal.TEN)
                        .ovpTotWesc(BigDecimal.ONE)
                        .ovp1Year(BigDecimal.TEN)
                        .ovp2Year(BigDecimal.ONE)
                        .ovpPrevWesc(BigDecimal.TEN)
                        .ovpVioWesc(BigDecimal.ONE)
                        .ovpNonVioWesc(BigDecimal.TEN)
                        .ovpAgeWesc(BigDecimal.ONE)
                        .ovpSexWesc(BigDecimal.TEN)
                        .group(aGroup("CURRENT"))
                        .assessmentStatus(anAssessmentStatus("COMPLETE"))
                        .assessmentVoidedDate(LocalDateTime.now())
                        .basicSentencePlanList(aSentencePlan(2L))
                        .offenceBlock(anOffenceBlock())
                        .build());
    }

    private static Set<OffenceBlock> anOffenceBlock() {
        return Set.of(OffenceBlock.builder()
                .offenceBlockPk(1L)
                .offenceSentenceDetail(OffenceSentenceDetail
                        .builder()
                        .activityDesc("activity")
                        .cjaSupervisionMonths(10L)
                        .cjaUnpaidHours(10L)
                        .build())
                .sentence(Sentence
                        .builder()
                        .cjaInd("Y")
                        .custodialInd("Y")
                        .endDate(LocalDate.MAX)
                        .orderType(RefElement.builder().refElementDesc("orderType").build())
                        .sentenceCode("sentenceCode")
                        .sentenceDesc("sentenceDesc")
                        .startDate(LocalDate.MIN)
                        .build()
                )
                .build(),
                OffenceBlock.builder()
                        .offenceBlockPk(2L)
                        .offenceSentenceDetail(OffenceSentenceDetail
                                .builder()
                                .activityDesc("another activity")
                                .cjaSupervisionMonths(5L)
                                .cjaUnpaidHours(5L)
                                .build())
                        .sentence(Sentence
                                .builder()
                                .cjaInd("Y")
                                .custodialInd("Y")
                                .endDate(LocalDate.MAX)
                                .orderType(RefElement.builder().refElementDesc("orderType").build())
                                .sentenceCode("310")
                                .sentenceDesc("Life")
                                .startDate(LocalDate.MIN)
                                .build()
                        )
                        .build());
    }

    private static Set<BasicSentencePlanObj> aSentencePlan(long l) {
        return Set.of(
                BasicSentencePlanObj.builder()
                        .objectiveText("obj" + l)
                        .measureText("measure" + l)
                        .timescalesText("timescales" + l)
                        .whoWillDoWorkText("who" + l)
                        .whatWorkText("what" + l)
                        .oasysSetPk(l).build()
        );
    }

    private static RefElement anAssessmentStatus(String status) {
        return RefElement.builder().refElementCode(status).build();
    }

    private static OasysAssessmentGroup aGroup(String status) {
        return OasysAssessmentGroup.builder()
                .historicStatusELm(status)
                .build();
    }

    private static RefElement assessmentType(String type) {
        return RefElement.builder().refElementCode(type).build();
    }

    public static OasysSet anOasysSet(Long id) {
        return OasysSet.builder()
                .assessmentType(RefElement.builder().build())
                .group(OasysAssessmentGroup.builder().build())
                .assessmentStatus(RefElement.builder().build())
                .oasysSetPk(id).build();
    }

}
