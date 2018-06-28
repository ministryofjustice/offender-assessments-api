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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RefAssessmentService {
    private final RefAssessmentRepository refAssessmentRepository;

    @Autowired
    public RefAssessmentService(RefAssessmentRepository refAssessmentRepository) {
        this.refAssessmentRepository = refAssessmentRepository;
    }

    public Optional<ReferenceAssessment> getReferenceAssessmentOf(String type, String revision) {

        Optional<RefAssVersion> maybeRefAssVersion = Optional.ofNullable(refAssessmentRepository.findOne(RefAssVersionPK.builder()
                .refAssVersionCode(type)
                .versionNumber(revision)
                .build()));

        return referenceAssessmentof(maybeRefAssVersion);
    }

    public ReferenceAssessment referenceAssessmentof(RefAssVersion refAssVersion) {
        return ReferenceAssessment.builder()
                .refAssVersionCode(refAssVersion.getRefAssVersionCode())
                .refAssVersionId(refAssVersion.getRefAssVersionUk())
                .oasysFormVersion(refAssVersion.getOasysFormVersion())
                .oasysScoringAlgorithmVersion(refAssVersion.getOasysScoringAlgVersion())
                .versionNumber(refAssVersion.getVersionNumber())
                .refSections(refsectionsOf(refAssVersion.setRefSections()
                        .build();
    }

    public List<RefSection> refsectionsOf(List<RefSection> refSections) {
        return Optional.ofNullable(refSections)
                .map(refSectionList -> refSections
                        .stream()
                        .map(this::refSectionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public RefSection refSectionOf(RefSection refSection) {
        return RefSection.builder()
                .refAssVersionCode(refSection.getRefAssVersionCode())
                .refCrimNeedScoreThreshold(refSection.getRefCrimNeedScoreThreshold())
                .refFormSequence(refSection.getRefFormSequence())
                .refScoredForOgp(refSection.isRefScoredForOgp())
                .refScoredForOvp(refSection.isRefScoredForOvp())
                .refSectionCode(refSection.getRefSectionCode())
                .refSectionId(refSection.getRefSectionId())
                .refSectionTypeCat(refSection.getRefSectionTypeCat())
                .refSectionTypeElm(refSection.getRefSectionTypeElm())
                .refVersionCode(refSection.getRefVersionCode())
                .refQuestions(refQuestionsOf(refSection.getRefQuestions()))
                .build();
    }

    public List<RefQuestion> refQuestionsOf(List<RefQuestion> refQuestions) {
        return Optional.ofNullable(refQuestions)
                .map(refQuestionList -> refQuestions
                        .stream()
                        .map(this::refQuestionOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public RefQuestion refQuestionOf(RefQuestion refQuestion) {
        return RefQuestion.builder()
                .refAssVersionCode(refQuestion.getRefAssVersionCode())
                .refCtAreaEstCode(refQuestion.getRefCtAreaEstCode())
                .refDisplaySort(refQuestion.getRefDisplaySort())
                .refMandatoryIndicator(refQuestion.isRefMandatoryIndicator())
                .refQAWeighting(refQuestion.getRefQAWeighting())
                .refQuestionCode(refQuestion.getRefQuestionCode())
                .refQuestionId(refQuestion.getRefQuestionId())
                .refSectionCode(refQuestion.getRefSectionCode())
                .refSectionQuestion(refQuestion.getRefSectionQuestion())
                .refVersionId(refQuestion.getRefVersionId())
                .refAnswers(refAnswerOf(refQuestion.getRefAnswers())
                        .build();
    }

    public List<RefAnswer> refAnswerOf(RefAnswer refAnswer) {
        return Optional.ofNullable(refAnswer)
                .map(refAnswer1 -> RefAnswer.builder()
                        .refAnswerCode(refAnswer.getRefAnswerCode())
                        .refAnswerId(refAnswer.getRefAnswerId())
                        .refAssVersionCode(refAnswer.getRefAssVersionCode())
                        .refDisplaySort(refAnswer.getRefDisplaySort())
                        .refSectionCode(refAnswer.getRefSectionCode())
                        .refVersionId(refAnswer.getRefVersionId())
                        .build());
    }
}

