package uk.gov.justice.digital.oasys.controller;

import com.google.common.collect.ImmutableList;
import org.mockito.Mockito;
import uk.gov.justice.digital.oasys.jpa.entity.*;
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
        Mockito.when(offenderRepository.findById(eq(3L))).thenReturn(assessedOffender());

    }

    private static Optional<Offender> anOffender() {
        return Optional.ofNullable(Offender.builder()
                .oasysAssessmentGroups(anAssessmentGroup())
                .build());
    }

    private static Optional<Offender> assessedOffender() {
        return Optional.ofNullable(Offender.builder()
                .oasysAssessmentGroups(anAssessmentGrouWithSingleSet())
                .build());
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

    public static OasysSet layer3AssessmentOasysSet(Long id) {
        return OasysSet.builder()
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .oasysSections(completeLayer3AssessmentSections())
                .assessmentStatus(RefElement.builder().build())
                .oasysSetPk(id).build();
    }

    public static List<OasysSection> completeLayer3AssessmentSections() {



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
                .oasysQuestions(List.of(question1098, question1099)).build();
        return List.of( section10);
    }

}
