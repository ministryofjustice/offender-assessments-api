package uk.gov.justice.digital.oasys.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysBcsPart;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSection;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.entity.QaReview;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;

@Component
public class AssessmentsTransformer {

    public Assessment assessmentOf(OasysSet oasysSet) {
        return Assessment.builder()
                .createdDateTime(localDateTimeOf(oasysSet.getCreateDate()))
                .assessmentType(Optional.ofNullable(oasysSet.getAssessmentType()).map(RefElement::getRefElementCode).orElse(null))
                .assessmentVersion(oasysSet.getRefAssVersion() == null ? null : AssessmentVersionDto.from(oasysSet.getRefAssVersion()))
                .completed(Optional.ofNullable(oasysSet.getDateCompleted()).isPresent())
                .completedDateTime(localDateTimeOf(oasysSet.getDateCompleted()))
                .oasysSetId(oasysSet.getOasysSetPk())
                .oasysBcsParts(oasysBcsPartsOf(oasysSet.getOasysBcsParts()))
                .qaReview(QaReviewOf(oasysSet.getQaReview()))
                .sections(sectionsOf(oasysSet.getOasysSections()))
                .voided(Optional.ofNullable(oasysSet.getAssessmentVoidedDate()).isPresent())
                .historicStatus(oasysSet.getGroup().getHistoricStatusELm())
                .assessmentStatus(Optional.ofNullable(oasysSet.getAssessmentStatus()).map(RefElement::getRefElementCode).orElse(null))
                .build();
    }

    public Map<String, Section> sectionsOf(Set<OasysSection> oasysSections) {
        return Optional.ofNullable(oasysSections)
                .map(sections -> sections
                        .stream()
                        .map(this::sectionOf)
                        .collect(Collectors.toMap(Section::getRefSectionCode, section -> section)))
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
                .lowScoreAttentionNeeded(ynToBoolean(section.getLowScoreNeedAttnInd()))
                .questions(questionsOf(section.getOasysQuestions()))
                .refSection(refSectionOf(section.getRefSection()))
                .build();
    }

    public RefSection refSectionOf(uk.gov.justice.digital.oasys.jpa.entity.RefSection refSection) {
        return RefSection.builder()
                .refCrimNeedScoreThreshold(refSection.getCrimNeedScoreThreshold())
                .refFormSequence(refSection.getFormSequence())
                .refScoredForOgp(ynToBoolean(refSection.getScoredForOgp()))
                .refScoredForOvp(ynToBoolean(refSection.getScoredForOvp()))
                .refSectionCode(refSection.getRefSectionCode())
                .shortDescription(refSection.getSectionType().getRefElementShortDesc()).build();
    }

    public Map<String, Question> questionsOf(Set<OasysQuestion> oasysQuestions) {
        return Optional.ofNullable(oasysQuestions)
                .map(sections -> sections
                        .stream()
                        .map(this::questionOf)
                        .collect(Collectors.toMap(Question::getRefQuestionCode, question -> question)))
                .orElse(null);
    }

    public Question questionOf(OasysQuestion question) {
        return Question.builder()
                .refQuestionId(question.getRefQuestion().getRefQuestionUk())
                .refQuestionCode(question.getRefQuestion().getRefQuestionCode())
                .oasysQuestionId(question.getOasysQuestionPk())
                .displayScore(question.getDisplayScore())
                .questionText(question.getRefQuestion().getRefSectionQuestion())
                .answer(Optional.ofNullable(answerOf(question, question.getOasysAnswer())))
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

    public List<uk.gov.justice.digital.oasys.api.OasysBcsPart> oasysBcsPartsOf(Set<OasysBcsPart> oasysBcsParts) {
        return Optional.ofNullable(oasysBcsParts)
                .map(obpo -> oasysBcsParts
                        .stream()
                        .map(this::oasysBcsPartOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private uk.gov.justice.digital.oasys.api.OasysBcsPart oasysBcsPartOf(OasysBcsPart oasysBcsPart) {
        return Optional.ofNullable(oasysBcsPart)
                .map(oasysBcsPart1 -> uk.gov.justice.digital.oasys.api.OasysBcsPart.builder()
                        .bcsPartCompDate(localDateTimeOf(oasysBcsPart.getCreateDate()))
                        .bcsPart(oasysBcsPart.getBcsPart())
                        .bcsPartStatus(oasysBcsPart.getBcsPartStatus())
                        .bcsPartUserArea(oasysBcsPart.getBcsPartUserArea())
                        .bcsPartUserPosition(oasysBcsPart.getBcsPartUserPosition())
                        .createDate(localDateTimeOf(oasysBcsPart.getCreateDate()))
                        .createUser(oasysBcsPart.getCreateUser())
                        .lastupdDate(localDateTimeOf(oasysBcsPart.getLastupdDate()))
                        .lastupdUser(oasysBcsPart.getLastupdUser())
                        .lockIncompleteOtherText(oasysBcsPart.getLockIncompleteOtherText())
                        .lockIncompleteReason(oasysBcsPart.getLockIncompleteReason())
                        .part1CheckedDate(localDateTimeOf(oasysBcsPart.getPart1CheckedDate()))
                        .part1CheckedInd(oasysBcsPart.getPart1CheckedInd())
                        .praCompDate(localDateTimeOf(oasysBcsPart.getPraCompDate()))
                        .praComplete(oasysBcsPart.getPraComplete())
                        .praCompUser(oasysBcsPart.getPraCompUser())
                        .bcsPartUser(oasysUserOf(oasysBcsPart.getBcsPartUser()))
                        .part1CheckedUser(oasysUserOf(oasysBcsPart.getPart1CheckedUser()))
                        .build()
                )
                .orElse(null);
    }

    private uk.gov.justice.digital.oasys.api.OasysUser oasysUserOf(OasysUser oasysUser) {
        return Optional.ofNullable(oasysUser)
                .map(oasysUser1 -> uk.gov.justice.digital.oasys.api.OasysUser.builder()
                        .oasysUserId(oasysUser.getOasysUserCode())
                        .userName(oasysUser.getUserForename1() + " " + oasysUser.getUserFamilyName())
                        .build()
                )
                .orElse(null);
    }

    private uk.gov.justice.digital.oasys.api.QaReview QaReviewOf(QaReview qaReview) {
        return Optional.ofNullable(qaReview)
                .map(qaReview1 -> uk.gov.justice.digital.oasys.api.QaReview.builder()
                        .currentlyHidden(ynToBoolean(qaReview.getCurrentlyHidden()))
                        .dateCompleted(localDateTimeOf(qaReview.getDateCompleted()))
                        .dateSelected(localDateTimeOf(qaReview.getDateSelected()))
                        .displaySort(qaReview.getDisplaySort())
                        .qaGrading(qaReview.getQaGrading())
                        .qaScore(qaReview.getQaScore())
                        .qaStatus(qaReview.getQaStatus())
                        .qaSubstitutionReason(qaReview.getQaSubstitutionReason())
                        .qaUser(oasysUserOf(qaReview.getQaUser()))
                        .refPeriodMonth(qaReview.getRefPeriodMonth())
                        .refPeriodQtr(qaReview.getRefPeriodQtr())
                        .refPeriodYear(qaReview.getRefPeriodYear())
                        .build()
                )
                .orElse(null);
    }

    public Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilterOf(Optional<String> filterAssessmentStatus, Optional<String> filterAssessmentType, Optional<String> filterGroupStatus, Optional<Boolean> filterVoided) {

        return filterAssessmentStatus.map(
                assessmentStatus -> curry(AssessmentFilters.byAssessmentStatus, assessmentStatus))
                .orElse(AssessmentFilters.identity)
                .andThen(
                        filterAssessmentType.map(
                                assessmentType -> curry(AssessmentFilters.byAssessmentType, assessmentType))
                                .orElse(AssessmentFilters.identity))
                .andThen(
                        filterGroupStatus.map(
                                groupStatus -> curry(AssessmentFilters.byGroupStatus, groupStatus))
                                .orElse(AssessmentFilters.identity))
                .andThen(
                        filterVoided.map(
                                voided -> curry(AssessmentFilters.byVoided, voided))
                                .orElse(AssessmentFilters.identity));
    }

    private static LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(t -> t.toLocalDateTime())
                .orElse(null);
    }

    private static Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn)
                .map("Y"::equalsIgnoreCase)
                .orElse(null);
    }

}