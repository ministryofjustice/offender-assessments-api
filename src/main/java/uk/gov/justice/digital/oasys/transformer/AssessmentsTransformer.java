package uk.gov.justice.digital.oasys.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.entity.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssessmentsTransformer {

    public final TypesTransformer typesTransformer;

    @Autowired
    public AssessmentsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public Assessment assessmentOf(OasysSet oasysSet) {
        return Assessment.builder()
                .createdDateTime(typesTransformer.localDateTimeOf(oasysSet.getCreateDate()))
                .assessmentType(oasysSet.getAssessmentType().getRefElementCode())
                .assessmentVersion(assessmentVersionOf(oasysSet.getRefAssVersion()))
                .completed(oasysSet.getDateCompleted() != null)
                .completedDateTime(typesTransformer.localDateTimeOf(oasysSet.getDateCompleted()))
                .oasysSetId(oasysSet.getOasysSetPk())
                .sections(sectionsOf(oasysSet.getOasysSections()))
                .voided(oasysSet.getAssessmentVoidedDate() != null)
                .historicStatus(oasysSet.getGroup().getHistoricStatusELm())
                .assessmentStatus(oasysSet.getAssessmentStatus().getRefElementCode())
                .build();
    }

    public List<Section> sectionsOf(List<OasysSection> oasysSections) {
        return Optional.ofNullable(oasysSections)
                .map(sections -> sections
                        .stream()
                        .map(this::sectionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public Section sectionOf(OasysSection section) {
        return Section.builder()
                .refSectionCode(section.getRefSection().getRefSectionCode())
                .oasysSectionId(section.getOasysSectionPk())
                .status(section.getSectionStatusElm())
                .sectionOgpWeightedScore(section.getSectOgpWeightedScore())
                .sectionOgpRawScore(section.getSectOgpRawScore())
                .sectionOvpWeightedScore(section.getSectOvpWeightedScore())
                .sectionOvpRawScore(section.getSectOvpRawScore())
                .sectionOtherWeightedScore(section.getSectOtherWeightedScore())
                .sectionOtherRawScore(section.getSectOtherRawScore())
                .lowScoreAttentionNeeded(typesTransformer.ynToBoolean(section.getLowScoreNeedAttnInd()))
                .questions(questionsOf(section.getOasysQuestions()))
                .build();
    }

    public List<Question> questionsOf(List<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions)
                .map(sections -> sections
                        .stream()
                        .map(this::questionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public Question questionOf(OasysQuestion question) {
        return Question.builder()
                .refQuestionId(question.getRefQuestion().getRefQuestionUk())
                .refQuestionCode(question.getRefQuestion().getRefQuestionCode())
                .oasysQuestionId(question.getOasysQuestionPk())
                .displayScore(question.getDisplayScore())
                .questionText(question.getRefQuestion().getRefSectionQuestion())
                .answer(answerOf(question, question.getOasysAnswer()))
                .build();
    }

    public Answer answerOf(OasysQuestion question, OasysAnswer oasysAnswer) {
        return Optional.ofNullable(oasysAnswer)
                .map(answer -> Answer.builder()
                        .refAnswerId(oasysAnswer.getRefAnswer().getRefAnswerUk())
                        .refAnswerCode(oasysAnswer.getRefAnswer().getRefAnswerCode())
                        .oasysAnswerId(oasysAnswer.getOasysAnswerPk())
                        .staticText(oasysAnswer.getRefAnswer().getRefSectionAnswer())
                        .freeformText(oasysAnswer.getOasysQuestion().getFreeFormatAnswer())
                        .ogpScore(oasysAnswer.getRefAnswer().getOgpScore())
                        .ovpScore((oasysAnswer.getRefAnswer().getOvpScore()))
                        .qaRawScore(oasysAnswer.getRefAnswer().getQaRawScore())
                        .build())
                .orElse(freeformAnswerof(question));
    }

    public Answer freeformAnswerof(OasysQuestion question) {
        return Optional.ofNullable(question).map(q -> Answer
                .builder()
                .freeformText(q.getFreeFormatAnswer())
                .build()).orElse(null);
    }

    public AssessmentVersion assessmentVersionOf(RefAssVersion refAssVersion) {
        return Optional.ofNullable(refAssVersion).map(
                version -> AssessmentVersion.builder()
                        .oasysFormVersion(refAssVersion.getOasysFormVersion())
                        .oasysScoringAlgorithmVersion(refAssVersion.getOasysScoringAlgVersion())
                        .refAssVersionCode(refAssVersion.getRefAssVersionCode())
                        .refAssVersionId(refAssVersion.getRefAssVersionUk())
                        .refModuleCode(refAssVersion.getRefModuleCode())
                        .versionNumber(refAssVersion.getVersionNumber())
                        .build()
        ).orElse(null);
    }
}