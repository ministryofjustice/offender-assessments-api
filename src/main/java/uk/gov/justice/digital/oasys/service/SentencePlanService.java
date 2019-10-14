package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;
import uk.gov.justice.digital.oasys.transformer.BasicSentencePlanTransformer;
import uk.gov.justice.digital.oasys.transformer.SentencePlanTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;

@Service
@Transactional
public class SentencePlanService {
    private final OffenderRepository offenderRepository;
    private final BasicSentencePlanTransformer basicSentencePlanTransformer;
    private final SentencePlanTransformer properSentencePlanTransformer;

    @Autowired
    public SentencePlanService(OffenderRepository offenderRepository, BasicSentencePlanTransformer basicSentencePlanTransformer, SentencePlanTransformer properSentencePlanTransformer) {
        this.offenderRepository = offenderRepository;
        this.basicSentencePlanTransformer = basicSentencePlanTransformer;
        this.properSentencePlanTransformer = properSentencePlanTransformer;
    }

    private Optional<BasicSentencePlan> latestBasicSentencePlanOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> getOasysSetStream(assessmentsFilter, offender)
                .max(Comparator.comparing(OasysSet::getCreateDate))
                .flatMap(basicSentencePlanTransformer::basicSentencePlanOf)
                .orElse(null));
    }

    private Stream<OasysSet> getOasysSetStream(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Offender offender) {
        return offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()));
    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffenderPk(Long oasysOffenderId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderId);

        return latestBasicSentencePlanOf(assessmentsFilter, maybeOffender);
    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffenderCrn(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return latestBasicSentencePlanOf(assessmentsFilter, maybeOffender);

    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return latestBasicSentencePlanOf(assessmentsFilter, maybeOffender);
    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffenderNomsId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return latestBasicSentencePlanOf(assessmentsFilter, maybeOffender);

    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return latestBasicSentencePlanOf(assessmentsFilter, maybeOffender);
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

    public Optional<List<BasicSentencePlan>> getBasicSentencePlansForOffenderPk(Long oasysOffenderId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderId);

        return basicSentencePlansOf(assessmentsFilter, maybeOffender);
    }

    private Optional<List<BasicSentencePlan>> basicSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(o -> getOasysSetStream(assessmentsFilter, o)
                .map(basicSentencePlanTransformer::basicSentencePlanOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    public Optional<List<BasicSentencePlan>> getBasicSentencePlansForOffenderCrn(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return basicSentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<BasicSentencePlan>> getBasicSentencePlansForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return basicSentencePlansOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<BasicSentencePlan>> getBasicSentencePlansForOffenderNomsId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return basicSentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<BasicSentencePlan>> getBasicSentencePlansForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return basicSentencePlansOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<ProperSentencePlan>> getProperSentencePlansForOffenderPk(Long oasysOffenderId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderId);

        return properSentencePlansOf(assessmentsFilter, maybeOffender);
    }

    private Optional<List<ProperSentencePlan>> properSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(o -> getOasysSetStream(assessmentsFilter, o)
                .map(properSentencePlanTransformer::sentencePlanOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    public Optional<List<ProperSentencePlan>> getProperSentencePlansForOffenderCrn(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return properSentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<ProperSentencePlan>> getProperSentencePlansForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return properSentencePlansOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<ProperSentencePlan>> getProperSentencePlansForOffenderNomsId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return properSentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<ProperSentencePlan>> getProperSentencePlansForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return properSentencePlansOf(assessmentsFilter, maybeOffender);
    }
}