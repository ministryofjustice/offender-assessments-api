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
import uk.gov.justice.digital.oasys.transformer.BasicSentencePlanTransformer;

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
    private final BasicSentencePlanTransformer sentencePlanTransformer;
    private final OasysAssessmentGroupRepository assessmentGroupRepository;
    private final CmsStubOffenderRepository cmsStubOffenderRepository;

    @Autowired
    public SentencePlanService(OffenderRepository offenderRepository, OffenderService offenderService, OasysSetRepository oasysSetRepository, BasicSentencePlanRepository basicSentencePlanRepository, BasicSentencePlanTransformer sentencePlanTransformer, OasysAssessmentGroupRepository assessmentGroupRepository, CmsStubOffenderRepository cmsStubOffenderRepository) {
        this.offenderRepository = offenderRepository;
        this.offenderService = offenderService;
        this.oasysSetRepository = oasysSetRepository;
        this.basicSentencePlanRepository = basicSentencePlanRepository;
        this.sentencePlanTransformer = sentencePlanTransformer;
        this.assessmentGroupRepository = assessmentGroupRepository;
        this.cmsStubOffenderRepository = cmsStubOffenderRepository;
    }

    @Transactional
    public Optional<BasicSentencePlan> createBasicSentencePlanForOffender(Offender offender) {
        Optional<OasysAssessmentGroup> latestGroup = latestAssessmentGroupForOffender(offender);
        Optional<OasysSet> latestSet = latestOasysSetOf(latestGroup);

        Optional<OasysSet> newSet = createSetFrom(latestSet);

        return newSet.map(s -> BasicSentencePlan
                .builder()
                .sentencePlanId(s.getOasysSetPk())
                .build());

    }

    @Transactional
    public Optional<BasicSentencePlanItem> addBasicSentencePlanItem(Long sentencePlanId, SentencePlanNeeds spratSpCode, BasicSentencePlanItem item) {

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

    public Optional<BasicSentencePlan> createBasicSentencePlanForOffenderPk(Long oasysOffenderId) {
        return offenderRepository.findById(oasysOffenderId).flatMap(this::createBasicSentencePlanForOffender);
    }


    public Optional<BasicSentencePlan> createBasicSentencePlanForOffenderCrn(String crn) {
        return offenderRepository.getByCmsProbNumber(crn).flatMap(this::createBasicSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createBasicSentencePlanForOffenderPnc(String pnc) {
        return offenderRepository.getByPnc(pnc).flatMap(this::createBasicSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createBasicSentencePlanForOffenderNomisId(String nomisId) {
        return offenderRepository.getByCmsPrisNumber(nomisId).flatMap(this::createBasicSentencePlanForOffender);
    }

    public Optional<BasicSentencePlan> createBasicSentencePlanForOffenderBookingId(String nomisId) {
        return offenderRepository.getByPrisonNumber(nomisId).flatMap(this::createBasicSentencePlanForOffender);
    }

    private Optional<BasicSentencePlan> latestBasicSentencePlanOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, Optional<Offender> maybeOffender) {
        return maybeOffender.map(offender -> getOasysSetStream(assessmentsFilter, offender)
                .max(Comparator.comparing(OasysSet::getCreateDate))
                .flatMap(sentencePlanTransformer::basicSentencePlanOf)
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
                .map(sentencePlanTransformer::basicSentencePlanOf)
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
}
