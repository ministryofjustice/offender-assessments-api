package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.AssessmentResource;
import uk.gov.justice.digital.oasys.api.Question;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;
import uk.gov.justice.digital.oasys.transformer.AssessmentsTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssessmentsService {

    private final OffenderRepository offenderRepository;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentsTransformer assessmentsTransformer;

    @Autowired
    public AssessmentsService(OffenderRepository offenderRepository, AssessmentRepository assessmentRepository, AssessmentsTransformer assessmentsTransformer) {
        this.offenderRepository = offenderRepository;
        this.assessmentRepository = assessmentRepository;
        this.assessmentsTransformer = assessmentsTransformer;
    }

    public Optional<List<AssessmentResource>> getAssessmentsForOffenderPK(Long oasysOffenderPk, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(oasysOffenderPk));

        return assessmentResourceOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<AssessmentResource>> getAssessmentsForOffenderCRN(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return assessmentResourceOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<AssessmentResource>> getAssessmentsForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPnc(pnc);

        return assessmentResourceOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<AssessmentResource>> getAssessmentsForOffenderNomisId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsPrisNumber(nomisId);

        return assessmentResourceOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<AssessmentResource>> getAssessmentsForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPrisonNumber(bookingId);

        return assessmentResourceOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderPk(Long oasysOffenderPk, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(oasysOffenderPk));

    return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderCRN(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPnc(pnc);

        return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }
    public Optional<Assessment> getLatestAssessmentForOffenderNomisId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsPrisNumber(nomisId);

        return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }
    public Optional<Assessment> getLatestAssessmentForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPrisonNumber(bookingId);

        return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }

    private Optional<List<AssessmentResource>> assessmentResourceOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream())
                                .sorted(Comparator.comparing(OasysSet::getCreateDate).reversed())
                                .map(assessmentsTransformer::assessmentResourceOf))
                .collect(Collectors.toList()));
    }

    private Optional<Assessment> latestAssessmentOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()))
                .max(Comparator.comparing(OasysSet::getCreateDate))
                .flatMap(a -> Optional.ofNullable(assessmentsTransformer.assessmentOf(a)))
                .orElse(null));
    }

    public Optional<Assessment> getAssessment(Long oasysSetId) {
        return Optional.ofNullable(assessmentRepository.findOne(oasysSetId)).map(assessmentsTransformer::assessmentOf);
    }

    public Optional<Question> getLatestQAndAforOffenderCRN(String crn, String assessmentType, String section, String question) {

        final Optional<Assessment> maybeAssessment = latestAssessmentOf(AssessmentFilters.curry(AssessmentFilters.byAssessmentType, assessmentType), offenderRepository.findByCmsProbNumber(crn));

        return maybeAssessment.flatMap(assessment -> assessment
                .getSections()
                .stream()
                .filter(s -> s.getRefSectionCode().equals(section))
                .findFirst()
                .flatMap( s -> s.getQuestions()
                        .stream()
                        .filter(q -> q.getRefQuestionCode().equals(question))
                        .findFirst())
        );
    }
}

