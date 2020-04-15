package uk.gov.justice.digital.oasys.api;

import uk.gov.justice.digital.oasys.jpa.entity.*;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.service.OffenderService;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ApiTestContext {

    public static void setup(OffenderService offenderService) {

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




    public static Section getSentencePlanSection() {


        OasysQuestion questionIP1 = OasysQuestion.builder()
                .refQuestion(RefQuestion.builder()
                        .displaySort(1l)
                        .refQuestionCode("IP.1")
                .build()).build();

        OasysQuestion questionIP2 = OasysQuestion.builder().freeFormatAnswer("Free form answer")
                .additionalNote("Additional note")
                .refQuestion(RefQuestion.builder()
                        .displaySort(2l)
                        .refQuestionCode("IP.2")
                        .build()).build();


        OasysAnswer answerIP1 = OasysAnswer.builder().refAnswer(RefAnswer.builder().refAnswerCode("YES").refSectionAnswer("Yes").build()).build();

        questionIP1.setOasysAnswer(answerIP1);
        answerIP1.setOasysQuestion(questionIP1);

        var section =  Section.builder()
                    .refSection(RefSection.builder()
                            .refSectionCode("ISP")
                            .refQuestions(List.of(RefQuestion.builder()
                                    .refQuestionUk(1l)
                                    .refQuestionCode("IP.40")
                                    .displaySort(1l)
                                    .refSectionQuestion("Ref Question Text").build()))
                            .sectionType(refElementFrom("ISP", "Initial Sentence Plan", "Initial Sentence Plan")).build())
                    .oasysQuestions(Set.of(questionIP1, questionIP2)).build();




        return section;
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
                .criminogenicNeed(refElementFrom("I10", "Need 1", null))
                .displaySort(1l)
                .build();

        var need2 = SspCrimNeedObjPivot.builder()
                .sspCrimNeedObjPivotPk(2l)
                .criminogenicNeed(refElementFrom("I20", "Need 2", null))
                .displaySort(2l)
                .build();

        return Set.of(need1,need2);
    }

    public static Set<SspObjIntervenePivot> getInterventions(long objectiveInSetPK) {

        var intervention1 = SspObjIntervenePivot.builder()
                .sspObjectivesInSetPk(objectiveInSetPK)
                .sspObjIntervenePivotPk(1l)
                .sspInterventionInSet(SspInterventionInSet.builder()
                        .copiedForwardIndicator("Y")
                        .sspInterventionInSetPk(1l)
                        .interventionComment("Intervention Comment")
                        .intervention(refElementFrom("V1", "Intervention 1", "Inv 1"))
                        .timescaleForIntervention(RefElement.builder().refElementCode("ONE_MONTH").refElementDesc("One Month").build())
                        .sspInterventionMeasure(SspInterventionMeasure.builder().build())
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


    public static Assessment layer3AssessmentWithBasicSentencePlans(Long id) {
        return Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .basicSentencePlanList(Set.of(aBasicSentencePlan(1), aBasicSentencePlan(2)))
                .oasysSetPk(id).build();
    }


    private static BasicSentencePlanObj aBasicSentencePlan(long l) {
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

    public static Assessment layer3AssessmentWithFullSentencePlan(Long id) {

        Set<Section> sections = new HashSet<>();
        sections.addAll(layer3AssessmentSections());
        sections.add(getSentencePlanSection());

        return Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .group(AssessmentGroup.builder().build())
                .oasysSections(sections)
                .sspObjectivesInSets(getObjectivesInSet())
                .assessmentStatus("COMPLETED")
                .oasysSetPk(id).build();
    }

    public static Set<Section> layer3AssessmentSections() {

        var section10 = Section.builder()
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


}