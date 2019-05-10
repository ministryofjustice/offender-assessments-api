package uk.gov.justice.digital.oasys.controller;

import com.google.common.collect.ImmutableList;
import org.mockito.Mockito;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    private static Optional<Offender> anOffender() {
        return Optional.ofNullable(Offender.builder()
                .oasysAssessmentGroups(anAssessmentGroup())
                .build());
    }

    private static List<OasysAssessmentGroup> anAssessmentGroup() {
        return ImmutableList.of(OasysAssessmentGroup.builder()
                .oasysAssessmentGroupPk(1L)
                .oasysSets(someOasysSets())
                .build());
    }

    private static List<OasysSet> someOasysSets() {
        return ImmutableList.of(OasysSet.builder()
                        .createDate(new Timestamp(System.currentTimeMillis() - oneDay()))
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
                        .build(),
                OasysSet.builder()
                        .createDate(new Timestamp(System.currentTimeMillis()))
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
                        .assessmentVoidedDate(new Timestamp(System.currentTimeMillis()))
                        .basicSentencePlanList(aSentencePlan(2L))
                        .build());
    }

    private static List<BasicSentencePlanObj> aSentencePlan(long l) {
        return ImmutableList.of(
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

    private static long oneDay() {
        return 1000 * 60 * 60 * 24;
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
