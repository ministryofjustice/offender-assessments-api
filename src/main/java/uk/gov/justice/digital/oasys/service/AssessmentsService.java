package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Answer;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.AssessmentVersion;
import uk.gov.justice.digital.oasys.api.Question;
import uk.gov.justice.digital.oasys.api.Section;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSection;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.transformer.TypesTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssessmentsService {

    private final OffenderRepository offenderRepository;
    private final TypesTransformer typesTransformer;

    @Autowired
    public AssessmentsService(OffenderRepository offenderRepository, TypesTransformer typesTransformer) {
        this.offenderRepository = offenderRepository;
        this.typesTransformer = typesTransformer;
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderCRN(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream())
                                .map(oasysSet -> Assessment.builder()
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
                                        .build()))
                .sorted(Comparator.comparing(Assessment::getCreatedDateTime, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList()));
    }

    private List<Section> sectionsOf(List<OasysSection> oasysSections) {
        return Optional.ofNullable(oasysSections)
                .map(sections -> sections
                        .stream()
                        .map(this::sectionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private Section sectionOf(OasysSection section) {
        return Section.builder()
                .refSectionId(section.getRefSection().getRefSectionUk())
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

    private List<Question> questionsOf(List<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions)
                .map(sections -> sections
                        .stream()
                        .map(this::questionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private Question questionOf(OasysQuestion question) {
        return Question.builder()
                .refQuestionId(question.getRefQuestion().getRefQuestionUk())
                .refQuestionCode(question.getRefQuestion().getRefQuestionCode())
                .oasysQuestionId(question.getOasysQuestionPk())
                .displayScore(question.getDisplayScore())
                .questionText(question.getRefQuestion().getRefSectionQuestion())
                .answer(answerOf(question, question.getOasysAnswer()))
                .build();
    }

    private Answer answerOf(OasysQuestion question, OasysAnswer oasysAnswer) {
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

    private Answer freeformAnswerof(OasysQuestion question) {
        return Optional.ofNullable(question).map(q -> Answer
                .builder()
                .freeformText(q.getFreeFormatAnswer())
                .build()).orElse(null);
    }

    private AssessmentVersion assessmentVersionOf(RefAssVersion refAssVersion) {
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

