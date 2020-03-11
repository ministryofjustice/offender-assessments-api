package uk.gov.justice.digital.oasys.controller;

import com.google.common.collect.ImmutableList;
import org.mockito.Mockito;
import uk.gov.justice.digital.oasys.jpa.entity.*;
import uk.gov.justice.digital.oasys.service.OffenderService;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
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
                        .basicSentencePlanList(Set.of(aSentencePlan(1L), aSentencePlan(2L)))
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
                        .basicSentPlanObjPk(1L)
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

    private static RefElement assessmentType(String type) {
        return RefElement.builder().refElementCode(type).build();
    }

    public static OasysSet layer3AssessmentOasysSet(Long id) {
        return OasysSet.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .oasysSections(completeLayer3AssessmentSections())
                .assessmentStatus(RefElement.builder().build())
                .basicSentencePlanList(Set.of(aSentencePlan(1), aSentencePlan(2)))
                .oasysSetPk(id).build();
    }

    public static Set<OasysSection> completeLayer3AssessmentSections() {

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
                .oasysQuestions(getOASysQuestions()).build();
        return Set.of( section10);
    }

    public static Set<OasysQuestion> getOASysQuestions() {
        OasysQuestion question1098 = OasysQuestion.builder()
                .oasysQuestionPk(1l)
                .displayScore(1l)
                .refQuestion(RefQuestion.builder()
                        .refQuestionUk(1l)
                        .refSectionQuestion("Question 10.98")
                        .refQuestionCode("10.98")
                        .displaySort(1l).build()).build();

        OasysQuestion question1099 = OasysQuestion.builder()
                .oasysQuestionPk(2l)
                .displayScore(2l)
                .refQuestion(RefQuestion.builder()
                        .refQuestionUk(2l)
                        .refSectionQuestion("Question 10.99")
                        .refQuestionCode("10.99")
                        .displaySort(2l).build()).build();

        OasysAnswer answer1098 = OasysAnswer.builder().refAnswer(RefAnswer.builder().
                refAnswerCode("YES")
                .defaultDisplayScore(1l)
                .displaySort(1l)
                .refSectionAnswer("Yes").build()).build();

        OasysAnswer answer1099 = OasysAnswer.builder().refAnswer(RefAnswer.builder()
                .refAnswerCode("NO")
                .defaultDisplayScore(2l)
                .displaySort(2l)
                .ogpScore(1l)
                .ovpScore(2l)
                .qaRawScore(3l)
                .refSectionAnswer("No").build()).build();

        question1098.setOasysAnswer(answer1098);
        answer1098.setOasysQuestion(question1098);

        question1099.setOasysAnswer(answer1099);
        answer1099.setOasysQuestion(question1099);

        OasysQuestion questionIP2 = OasysQuestion.builder().freeFormatAnswer("Free form answer")
                .additionalNote("Additional note")
                .refQuestion(RefQuestion.builder()
                        .refQuestionCode("IP.1")
                        .displaySort(3l)
                        .refQuestionCode("IP.1")
                        .build()).build();

        return Set.of(question1098, question1099, questionIP2);
    }




    public static OasysSet layer3AssessmentOasysSetWithFullSentencePlan(Long id) {

        Set<OasysSection> sections = new HashSet<>();
        sections.addAll(completeLayer3AssessmentSections());
        sections.add(getSentencePlanSection());

        return OasysSet.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType(RefElement.builder().refElementCode("LAYER_3").build())
                .group(OasysAssessmentGroup.builder().build())
                .oasysSections(sections)

                .sspObjectivesInSets(getObjectivesInSet())
                .assessmentStatus(RefElement.builder().build())
                .basicSentencePlanList(Set.of(aSentencePlan(1), aSentencePlan(2)))
                .oasysSetPk(id).build();
    }

    public static OasysSection getSentencePlanSection() {


        OasysQuestion questionIP1 = OasysQuestion.builder()
                .refQuestion(RefQuestion.builder()
                        .refQuestionCode("IP.1")
                        .displaySort(1l)
                        .refQuestionCode("IP.2")
                .build()).build();

        OasysQuestion questionIP2 = OasysQuestion.builder().freeFormatAnswer("Free form answer")
                .additionalNote("Additional note")
                .refQuestion(RefQuestion.builder()
                        .refQuestionCode("IP.1")
                        .displaySort(2l)
                        .refQuestionCode("IP.1")
                        .build()).build();


        OasysAnswer answerIP1 = OasysAnswer.builder().refAnswer(RefAnswer.builder().refAnswerCode("YES").refSectionAnswer("Yes").build()).build();

        questionIP1.setOasysAnswer(answerIP1);
        answerIP1.setOasysQuestion(questionIP1);

        var section =  OasysSection.builder()
                    .refSection(RefSection.builder()
                            .refSectionCode("ISP")
                            .sectionType(refElementFrom("ISP", "Initial Sentence Plan", "Initial Sentence Plan")).build())
                    .oasysQuestions(Set.of(questionIP1, questionIP2)).build();

        return  section;
    }

    public static Set<SspObjectivesInSet> getObjectivesInSet() {

        var objective1 = SspObjectivesInSet.builder().objectiveType(refElementFrom("CURRENT",
                "Current", null ))
                .sspObjectiveMeasure(SspObjectiveMeasure.builder().objectiveStatus(
                        refElementFrom("R", "Ongoing", null))
                        .objectiveStatusComments("Status Comments")
                        .sspObjectiveMeasurePk(1l).build())
                .howProgressMeasured("Progress measured")
                .sspObjIntervenePivots(getInterventions(1l))
                .sspObjective(SspObjective.builder().objectiveDesc("Objective 1 description")
                        .sspObjectivePk(1l)
                        .objective(getObjective("100","Objective 1", "Objective 1 Heading")).build())
                .sspObjectivesInSetPk(1l)
                .createDate(LocalDateTime.of(2019, 12,28, 9, 00))
                .sspCrimNeedObjPivots(getNeeds()).build();

        var objective2 = SspObjectivesInSet.builder().objectiveType(refElementFrom("CURRENT",
                "Current", null ))
                .sspObjectiveMeasure(SspObjectiveMeasure.builder().objectiveStatus(
                        refElementFrom("R", "Ongoing", null))
                        .objectiveStatusComments("Status Comments")
                        .sspObjectiveMeasurePk(2l).build())
                .howProgressMeasured("Progress measured")
                .sspObjIntervenePivots(getInterventions(2l))
                .sspObjective(SspObjective.builder().objectiveDesc("Objective 2 description")
                        .sspObjectivePk(2l)
                        .objective(getObjective("200","Objective 2", "Objective 2 Heading")).build())
                .sspObjIntervenePivots(getInterventions(2l))
                .createDate(LocalDateTime.of(2019, 11,28, 9, 00))
                .sspObjectivesInSetPk(2l).build();


        return Set.of(objective1, objective2);


    }

    public static Set<SspCrimNeedObjPivot> getNeeds() {
        var need1 = SspCrimNeedObjPivot.builder()
                .sspCrimNeedObjPivotPk(1l)
                .criminogenicNeed(refElementFrom("I10", "Need 1", null)).build();

        var need2 = SspCrimNeedObjPivot.builder()
                .sspCrimNeedObjPivotPk(2l)
                .criminogenicNeed(refElementFrom("I20", "Need 2", null)).build();

        return Set.of(need1,need2);
    }

    public static Set<SspObjIntervenePivot> getInterventions(long objectiveInSetPK) {

        var intervention1 = SspObjIntervenePivot.builder()
                .sspObjectivesInSetPk(objectiveInSetPK)
                .sspObjIntervenePivotPk(1l)
                .sspInterventionInSet(SspInterventionInSet.builder()
                        .sspInterventionInSetPk(1l)
                        .interventionComment("Intervention Comment")
                        .intervention(refElementFrom("V1", "Intervention 1", "Inv 1"))
                        .sspWhoDoWorkPivot(Set.of(SspWhoDoWorkPivot.builder()
                                .sspWhoDoWorkPivotPk(1l)
                                .comments("Who do work comment")
                                .whoDoWork(refElementFrom("IX1", "Offender", null)).build()
                        )).build()).build();

        var intervention2 = SspObjIntervenePivot.builder()
                .sspObjectivesInSetPk(objectiveInSetPK)
                .sspObjIntervenePivotPk(2l)
                .sspInterventionInSet(SspInterventionInSet.builder()
                        .sspInterventionInSetPk(2l)
                        .interventionComment("Intervention Comment")
                        .intervention(refElementFrom("V2", "Intervention 2", "Inv 2"))
                        .sspWhoDoWorkPivot(Set.of(SspWhoDoWorkPivot.builder()
                                .sspWhoDoWorkPivotPk(1l)
                                .comments("Who do work comment 2")
                                .whoDoWork(refElementFrom("IX1", "Prison Officer", null)).build()
                        )).build()).build();

        return Set.of(intervention1,intervention2);
    }


    public static Objective getObjective(String code, String description, String heading) {
        return Objective.builder()
                .objectiveCode(code)
                .objectiveDesc(description)
                .objectiveHeading(refElementFrom(code, heading, heading))
                .build();
    }

    public static RefElement refElementFrom(String code, String description, String shortDescription ) {
        return RefElement.builder()
                .refElementCode(code)
                .refElementDesc(description)
                .refElementShortDesc(shortDescription).build();
    }



}