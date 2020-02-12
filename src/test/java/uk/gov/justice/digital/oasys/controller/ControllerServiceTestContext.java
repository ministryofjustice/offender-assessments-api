package uk.gov.justice.digital.oasys.controller;

import com.google.common.collect.ImmutableList;
import org.mockito.Mockito;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import uk.gov.justice.digital.oasys.service.OffenderService;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ControllerServiceTestContext {

    public static void setup(OffenderService offenderService) {

        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("crn1"))).thenReturn(anOffender());
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("crn2"))).thenThrow(new ApplicationExceptions.EntityNotFoundException("Some Error", LogEvent.OFFENDER_NOT_FOUND));
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("nomisId1"))).thenReturn(anOffender());
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("nomisId2"))).thenThrow(new ApplicationExceptions.EntityNotFoundException("Some Error", LogEvent.OFFENDER_NOT_FOUND));
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("bookingId1"))).thenReturn(anOffender());
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("bookingId2"))).thenThrow(new ApplicationExceptions.EntityNotFoundException("Some Error", LogEvent.OFFENDER_NOT_FOUND));
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("pnc1"))).thenReturn(anOffender());
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("pnc2"))).thenThrow(new ApplicationExceptions.EntityNotFoundException("Some Error", LogEvent.OFFENDER_NOT_FOUND));
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("1"))).thenReturn(anOffender());
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("2"))).thenThrow(new ApplicationExceptions.EntityNotFoundException("Some Error", LogEvent.OFFENDER_NOT_FOUND));
        Mockito.when(offenderService.findOffenderAssessmentGroup(any(),eq("3"))).thenReturn(assessedOffender());

    }

    public static List<OasysAssessmentGroup>  anOffender() {
        return anAssessmentGroup();
    }

    private static List<OasysAssessmentGroup>  assessedOffender() {
        return anAssessmentGrouWithSingleSet();
    }

    private static List<OasysAssessmentGroup> anAssessmentGroup() {
        return ImmutableList.of(OasysAssessmentGroup.builder()
                .oasysAssessmentGroupPk(1L)
                .oasysSets(someOasysSets())
                .build());
    }

    private static List<OasysAssessmentGroup> anAssessmentGrouWithSingleSet() {
        return ImmutableList.of(OasysAssessmentGroup.builder()
                .oasysAssessmentGroupPk(1L)
                .oasysSets(List.of(layer3AssessmentOasysSet(1L)))
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
                        .basicSentencePlanList(Set.of(aSentencePlan(1L), aSentencePlan(2L)))
                        .offenceBlock(anOffenceBlock())
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
                        .basicSentencePlanList(Set.of(aSentencePlan(2L)))
                        .offenceBlock(anOffenceBlock())
                        .build());
    }

    private static Set<OffenceBlock> anOffenceBlock() {
        return Set.of(OffenceBlock.builder()
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
                .build());
    }

    private static BasicSentencePlanObj aSentencePlan(long l) {
                return BasicSentencePlanObj.builder()
                        .basicSentPlanObjPk(1l)
                        .includeInPlanInd("Y")
                        .createDate(LocalDateTime.now().minusDays(1))
                        .objectiveText("obj" + l)
                        .measureText("measure" + l)
                        .timescalesText("timescales" + l)
                        .whoWillDoWorkText("who" + l)
                        .whatWorkText("what" + l)
                        .dateOpened(LocalDateTime.now().minusDays(10))
                        .problemAreaCompInd("Y")
                        .offenceBehaviourLink(RefElement.builder().refElementShortDesc("LINK" + l).refElementDesc("Link" + l).build())
                        .oasysSetPk(l).build();
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

    public static OasysSet layer3AssessmentOasysSet(Long id) {
        return OasysSet.builder()
                .createDate(new Timestamp(System.currentTimeMillis() - oneDay()))
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .oasysSections(completeLayer3AssessmentSections())
                .assessmentStatus(RefElement.builder().build())
                .basicSentencePlanList(Set.of(aSentencePlan(1), aSentencePlan(2)))
                .oasysSetPk(id).build();
    }

    public static Set<OasysSection> completeLayer3AssessmentSections() {



        OasysQuestion question1098 = OasysQuestion.builder().freeFormatAnswer("Free form answer")
                .refQuestion(RefQuestion.builder().refQuestionCode("10.98").build()).build();

        OasysQuestion question1099 = OasysQuestion.builder().freeFormatAnswer("Free form answer")
                .refQuestion(RefQuestion.builder().refQuestionCode("10.99").build()).build();

        OasysAnswer answer1098 = OasysAnswer.builder().refAnswer(RefAnswer.builder().refAnswerCode("YES").build()).build();
        OasysAnswer answer1099 = OasysAnswer.builder().refAnswer(RefAnswer.builder().refAnswerCode("YES").build()).build();

        question1098.setOasysAnswer(answer1098);
        answer1098.setOasysQuestion(question1098);

        question1099.setOasysAnswer(answer1099);
        answer1099.setOasysQuestion(question1099);

        OasysSection section10 = OasysSection.builder()
                .refSection(RefSection.builder()
                        .crimNeedScoreThreshold(5L).refSectionCode("10")
                        .scoredForOgp("Y")
                        .scoredForOvp("Y")
                        .sectionType(
                        RefElement.builder().refElementCode("10").refElementShortDesc("Emotional Wellbeing").build()).build())
                .sectOvpRawScore(5L)
                .sectOgpRawScore(5L)
                .lowScoreNeedAttnInd("YES")
                .sectOtherRawScore(10L)
                .oasysQuestions(Set.of(question1098, question1099)).build();
        return Set.of( section10);
    }

}