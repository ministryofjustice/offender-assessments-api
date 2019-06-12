package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.BasicSentencePlanItem;
import uk.gov.justice.digital.oasys.api.SentencePlanNeeds;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;
import uk.gov.justice.digital.oasys.jpa.entity.CmsStubOffender;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.BasicSentencePlanRepository;
import uk.gov.justice.digital.oasys.jpa.repository.CmsStubOffenderRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAssessmentGroupRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysSetRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;
import uk.gov.justice.digital.oasys.transformer.SentencePlanTransformer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;

@Service
public class SentencePlanService {
    public static final String AREA_LINKED_TO_OFFENDING_BEHAVIOUR = "AREA_LINKED_TO_OFFENDING_BEHAVIOUR";
    public static final String ASSESSMENTS_API = "ASSESSMENTS_API";
    private final OffenderRepository offenderRepository;
    private final OffenderService offenderService;
    private final OasysSetRepository oasysSetRepository;
    private final BasicSentencePlanRepository basicSentencePlanRepository;
    private final SentencePlanTransformer sentencePlanTransformer;
    private final OasysAssessmentGroupRepository assessmentGroupRepository;
    private final CmsStubOffenderRepository cmsStubOffenderRepository;

    @Autowired
    public SentencePlanService(OffenderRepository offenderRepository, OffenderService offenderService, OasysSetRepository oasysSetRepository, BasicSentencePlanRepository basicSentencePlanRepository, SentencePlanTransformer sentencePlanTransformer, OasysAssessmentGroupRepository assessmentGroupRepository, CmsStubOffenderRepository cmsStubOffenderRepository) {
        this.offenderRepository = offenderRepository;
        this.offenderService = offenderService;
        this.oasysSetRepository = oasysSetRepository;
        this.basicSentencePlanRepository = basicSentencePlanRepository;
        this.sentencePlanTransformer = sentencePlanTransformer;
        this.assessmentGroupRepository = assessmentGroupRepository;
        this.cmsStubOffenderRepository = cmsStubOffenderRepository;
    }

    @Transactional
    public Optional<BasicSentencePlan> createSentencePlanForOffender(Offender offender) {
        Optional<OasysAssessmentGroup> latestGroup = latestAssessmentGroupForOffender(offender);
        Optional<OasysSet> latestSet = latestOasysSetOf(latestGroup);

        Optional<OasysSet> newSet = createSetFrom(latestSet);

        return newSet.map(s -> BasicSentencePlan
                .builder()
                .sentencePlanId(s.getOasysSetPk())
                .build());

    }

    @Transactional
    public Optional<BasicSentencePlanItem> addSentencePlanItem(Long sentencePlanId, SentencePlanNeeds spratSpCode, BasicSentencePlanItem item) {

        BasicSentencePlanObj basicSentencePlanObj = BasicSentencePlanObj
                .builder()
                .includeInPlanInd("Y")
                .createDate(Date.valueOf(LocalDate.now()))
                .lastupdDate(Date.valueOf(LocalDate.now()))
                .measureText(item.getMeasureText())
                .oasysSetPk(sentencePlanId)
                .objectiveText(item.getObjectiveText())
                .offenceBehaviourLink(RefElement.builder()
                        .refCategoryCode(AREA_LINKED_TO_OFFENDING_BEHAVIOUR)
                        .refElementCode(spratSpCode.toString())
                        .build())
                .timescalesText(item.getTimescalesText())
                .whatWorkText(item.getWhatWorkText())
                .whoWillDoWorkText(item.getWhoWillDoWorkText())
                .build();

        return Optional.of(basicSentencePlanRepository.save(basicSentencePlanObj))
                .map(sentencePlanTransformer::basicSentencePlanItemOf);

    }


    private Optional<OasysSet> createSetFrom(Optional<OasysSet> latestSet) {

        if (latestSet.isEmpty()) {
            return Optional.empty();
        }

        OasysSet newSet = OasysSet
                .builder()
                .group(latestSet.get().getGroup())
                .createDate(Timestamp.valueOf(LocalDateTime.now()))
                .psrClosedGroupInd("N")
                .assessmentStatus(RefElement.builder()
                        .refCategoryCode("ASSESSMENT_STATUS")
                        .refElementCode("OPEN")
                        .build())
                .refAssVersion(latestSet.get().getRefAssVersion())
                .invalidSect1Score("N")
                .cmsResendInd("N")
                .clonedLockedIncompInd("N")

                .build();

        return Optional.of(oasysSetRepository.save(newSet));
    }


    @Transactional
    public OasysSet newLayer3SetFor(Offender offender) {

        var group = latestAssessmentGroupForOffender(offender).orElse(newGroupFor(offender));
        var now = Timestamp.valueOf(LocalDateTime.now());
        var cmsStubOffender = Optional.ofNullable(offender.getCmsProbNumber()).flatMap(cmsStubOffenderRepository::findByCmsProbNumber);
        OasysSet newSet = OasysSet
                .builder()
                .assessorName(ASSESSMENTS_API)
                //TODO:
                .assessorTeam("tbd")
                //TODO:
                 .assessorOffice("tbd")
                //TODO:
                .assessorEmail("tbd")
                .assessmentType(RefElement.builder().refCategoryCode("ASSESSMENT_TYPE").refElementCode("LAYER_3").build())
                .assessorService(RefElement.builder().refCategoryCode("SERVICE").refElementCode("NPS").build())
                .dateOfBirth(offender.getDateOfBirth())
                .familyName(offender.getFamilyName())
                .forename1(offender.getForename1())
                .forename2(offender.getForename2())
                .forename3(offender.getForename3())
                .initiationDate(now)
                .pnc(Optional.ofNullable(offender.getPnc()).orElse("PNC UNKNOWN"))
                .psrClosedGroupInd("N")
                //TODO: "Other"
                .purposeAssessment(RefElement.builder().refCategoryCode("PURPOSE_OF_ASSESSMENT_REASON").refElementCode("400").build())
                .religion(religionOf(cmsStubOffender))
                .sspType(RefElement.builder().refCategoryCode("SENTENCE_PLAN_TYPE").refElementCode("REVIEW").build())
                .assessmentStatus(RefElement.builder()
                        .refCategoryCode("ASSESSMENT_STATUS")
                        .refElementCode("OPEN")
                        .build())
                .purposeAssmtOtherFtxt("TSP Assessment")
                .group(group)
                .createDate(now)
                .psrClosedGroupInd("N")
                .refAssVersion(RefAssVersion.builder().refAssVersionCode("LAYER3").versionNumber("1").build())
                .invalidSect1Score("N")
                .cmsResendInd("N")
                .clonedLockedIncompInd("Y")
                .postSentSupvDateInd("Y")
                .bcsSystemCreatedInd("N")
                .build();

        return oasysSetRepository.save(newSet);
    }

    private RefElement religionOf(Optional<CmsStubOffender> cmsStubOffender) {
        return cmsStubOffender.map(so -> RefElement.builder().refCategoryCode(so.getReligionCat()).refElementCode(so.getReligionElm()).build()).orElse(null);
    }

    private OasysAssessmentGroup newGroupFor(Offender offender) {
        var now = Timestamp.valueOf(LocalDateTime.now());

        return assessmentGroupRepository.save(OasysAssessmentGroup.builder()
                .historicStatusCat("HISTORIC_STATUS")
                .historicStatusELm("CURRENT")
                .offenderPk(offender.getOffenderPk())
                .createDate(now)
                .lastupdDate(now)
                .createUser(ASSESSMENTS_API)
                .lastupdUser(ASSESSMENTS_API)
                .build());
    }


    private Optional<OasysSet> latestOasysSetOf(Optional<OasysAssessmentGroup> latestGroup) {
        return latestGroup.flatMap(g -> g.getOasysSets().stream().max(Comparator.comparing(OasysSet::getCreateDate)));
    }

    public Optional<OasysAssessmentGroup> latestAssessmentGroupForOffender(Offender offender) {
        return offender.getOasysAssessmentGroups().stream().max(Comparator.comparing(OasysAssessmentGroup::getCreateDate));
    }

    public Optional<BasicSentencePlan> createSentencePlanForOffenderPk(Long oasysOffenderId) {
        return offenderRepository.findById(oasysOffenderId).flatMap(this::createSentencePlanForOffender);
    }


    public Optional<BasicSentencePlan> createSentencePlanForOffenderCrn(String crn) {
        return offenderRepository.getByCmsProbNumber(crn).flatMap(this::createSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createSentencePlanForOffenderPnc(String pnc) {
        return offenderRepository.getByPnc(pnc).flatMap(this::createSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createSentencePlanForOffenderNomisId(String nomisId) {
        return offenderRepository.getByCmsPrisNumber(nomisId).flatMap(this::createSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createSentencePlanForOffenderBookingId(String nomisId) {
        return offenderRepository.getByPrisonNumber(nomisId).flatMap(this::createSentencePlanForOffender);
    }

    private Optional<BasicSentencePlan> latestSentencePlanOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> getOasysSetStream(assessmentsFilter, offender)
                .max(Comparator.comparing(OasysSet::getCreateDate))
                .flatMap(sentencePlanTransformer::sentencePlanOf)
                .orElse(null));
    }

    private Stream<OasysSet> getOasysSetStream(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Offender offender) {
        return offender.getOasysAssessmentGroups()
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()));
    }

    public Optional<BasicSentencePlan> getLatestSentencePlanForOffenderPk(Long oasysOffenderId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderId);

        return latestSentencePlanOf(assessmentsFilter, maybeOffender);
    }

    public Optional<BasicSentencePlan> getLatestSentencePlanForOffenderCrn(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return latestSentencePlanOf(assessmentsFilter, maybeOffender);

    }

    public Optional<BasicSentencePlan> getLatestSentencePlanForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return latestSentencePlanOf(assessmentsFilter, maybeOffender);
    }

    public Optional<BasicSentencePlan> getLatestSentencePlanForOffenderNomsId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return latestSentencePlanOf(assessmentsFilter, maybeOffender);

    }

    public Optional<BasicSentencePlan> getLatestSentencePlanForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return latestSentencePlanOf(assessmentsFilter, maybeOffender);
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

    public Optional<List<BasicSentencePlan>> getSentencePlansForOffenderPk(Long oasysOffenderId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.findById(oasysOffenderId);

        return sentencePlansOf(assessmentsFilter, maybeOffender);
    }

    private Optional<List<BasicSentencePlan>> sentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(o -> getOasysSetStream(assessmentsFilter, o)
                .map(sentencePlanTransformer::sentencePlanOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    public Optional<List<BasicSentencePlan>> getSentencePlansForOffenderCrn(String crn, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsProbNumber(crn);

        return sentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<BasicSentencePlan>> getSentencePlansForOffenderPnc(String pnc, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPnc(pnc);

        return sentencePlansOf(assessmentsFilter, maybeOffender);
    }

    public Optional<List<BasicSentencePlan>> getSentencePlansForOffenderNomsId(String nomisId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByCmsPrisNumber(nomisId);

        return sentencePlansOf(assessmentsFilter, maybeOffender);

    }

    public Optional<List<BasicSentencePlan>> getSentencePlansForOffenderBookingId(String bookingId, Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter) {
        Optional<Offender> maybeOffender = offenderRepository.getByPrisonNumber(bookingId);

        return sentencePlansOf(assessmentsFilter, maybeOffender);
    }
}
