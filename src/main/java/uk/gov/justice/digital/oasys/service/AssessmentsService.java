package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
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
    private final AssessmentsTransformer assessmentsTransformer;

    @Autowired
    public AssessmentsService(OffenderRepository offenderRepository, AssessmentsTransformer assessmentsTransformer) {
        this.offenderRepository = offenderRepository;
        this.assessmentsTransformer = assessmentsTransformer;
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderPK(Long oasysOffenderPk, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(oasysOffenderPk));

        return assessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderCRN(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return assessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderPNC(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPnc(pnc);

        return assessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderNomisId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsPrisNumber(nomisId);

        return assessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<Assessment>> getAssessmentsForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByPrisonNumber(bookingId);

        return assessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderPk(Long oasysOffenderPk, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(oasysOffenderPk));

    return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderCRN(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findByCmsProbNumber(crn);

        return latestAssessmentOf(assessmentsFilter, maybeOffender);
    }

    public Optional<Assessment> getLatestAssessmentForOffenderPNC(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
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

    private Optional<List<Assessment>> assessmentOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream())
                                .sorted(Comparator.comparing(OasysSet::getCreateDate).reversed())
                                .map(assessmentsTransformer::assessmentOf))
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
}

