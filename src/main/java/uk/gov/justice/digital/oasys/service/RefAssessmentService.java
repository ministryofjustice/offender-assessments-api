package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.RefAnswer;
import uk.gov.justice.digital.oasys.api.RefQuestion;
import uk.gov.justice.digital.oasys.api.RefSection;
import uk.gov.justice.digital.oasys.api.ReferenceAssessment;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersionPK;
import uk.gov.justice.digital.oasys.jpa.repository.RefAssessmentRepository;
import uk.gov.justice.digital.oasys.transformer.TypesTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RefAssessmentService {
    private final RefAssessmentRepository refAssessmentRepository;
    private final TypesTransformer typesTransformer;

    @Autowired
    public RefAssessmentService(RefAssessmentRepository refAssessmentRepository, TypesTransformer typesTransformer) {
        this.refAssessmentRepository = refAssessmentRepository;
        this.typesTransformer = typesTransformer;
    }

    public Optional<ReferenceAssessment> getReferenceAssessmentOf(String type, String revision) {

        Optional<RefAssVersion> maybeRefAssVersion = refAssessmentRepository.findById(RefAssVersionPK.builder()
                .refAssVersionCode(type)
                .versionNumber(revision)
                .build());


        return maybeRefAssVersion.map(this::referenceAssessmentof);
    }

    public ReferenceAssessment referenceAssessmentof(RefAssVersion refAssVersion) {
        return ReferenceAssessment.builder()
                .refAssVersionCode(refAssVersion.getRefAssVersionCode())
                .refAssVersionId(refAssVersion.getRefAssVersionUk())
                .oasysFormVersion(refAssVersion.getOasysFormVersion())
                .oasysScoringAlgorithmVersion(refAssVersion.getOasysScoringAlgVersion())
                .versionNumber(refAssVersion.getVersionNumber())
                .refSections(refsectionsOf(refAssVersion.getRefSections()))
                .build();
    }

    public List<RefSection> refsectionsOf(List<uk.gov.justice.digital.oasys.jpa.entity.RefSection> refSections) {
        return Optional.ofNullable(refSections)
                .map(refSectionList -> refSections
                        .stream()
                        .map(this::refSectionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public RefSection refSectionOf(uk.gov.justice.digital.oasys.jpa.entity.RefSection refSection) {
        return RefSection.builder()
                .refCrimNeedScoreThreshold(refSection.getCrimNeedScoreThreshold())
                .refFormSequence(refSection.getFormSequence())
                .refScoredForOgp(typesTransformer.ynToBoolean(refSection.getScoredForOgp()))
                .refScoredForOvp(typesTransformer.ynToBoolean(refSection.getScoredForOgp()))
                .refSectionCode(refSection.getRefSectionCode())
                .refSectionId(refSection.getRefSectionUk())
                .refSectionType(refSection.getSectionTypeElm())
                .refQuestions(refQuestionsOf(refSection.getRefQuestions()))
                .build();
    }

    public List<RefQuestion> refQuestionsOf(List<uk.gov.justice.digital.oasys.jpa.entity.RefQuestion> refQuestions) {
        return Optional.ofNullable(refQuestions)
                .map(refQuestionList -> refQuestions
                        .stream()
                        .map(this::refQuestionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public RefQuestion refQuestionOf(uk.gov.justice.digital.oasys.jpa.entity.RefQuestion refQuestion) {
        return RefQuestion.builder()
                .refCtAreaEstCode(refQuestion.getCtAreaEstCode())
                .refDisplaySort(refQuestion.getDisplaySort())
                .refMandatoryIndicator(typesTransformer.ynToBoolean(refQuestion.getMandatoryInd()))
                .refQAWeighting(refQuestion.getQaWeighting())
                .refQuestionCode(refQuestion.getRefQuestionCode())
                .refQuestionId(refQuestion.getRefQuestionUk())
                .refQuestionText(refQuestion.getRefSectionQuestion())
                .refAnswers(refAnswersOf(refQuestion.getRefAnswers()))
                .build();
    }

    public List<RefAnswer> refAnswersOf(List<uk.gov.justice.digital.oasys.jpa.entity.RefAnswer> refAnswers) {
        return Optional.ofNullable(refAnswers)
                .map(ra -> ra
                        .stream()
                        .map(this::refAnswerOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public RefAnswer refAnswerOf(uk.gov.justice.digital.oasys.jpa.entity.RefAnswer refAnswer) {
        return Optional.ofNullable(refAnswer)
                .map(refAnswer1 -> RefAnswer.builder()
                        .refAnswerCode(refAnswer.getRefAnswerCode())
                        .refAnswerId(refAnswer.getRefAnswerUk())
                        .refDisplaySort(refAnswer.getDisplaySort())
                        .build())
                .orElse(null);

    }
}

