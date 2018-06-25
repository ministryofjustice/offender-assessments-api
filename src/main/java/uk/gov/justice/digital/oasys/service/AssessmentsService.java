package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.AssessmentVersion;
import uk.gov.justice.digital.oasys.api.Section;
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
                                        .build()))
                .sorted(Comparator.comparing(Assessment::getCreatedDateTime, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList()));
    }

    private List<Section> sectionsOf(List<OasysSection> oasysSections) {
        return Optional.ofNullable(oasysSections)
                .map(sections -> sections
                        .stream()
                        .map(section -> sectionOf(section))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    private Section sectionOf(OasysSection section) {
        return Section.builder()
                //TODO
                .build();
    }

    private AssessmentVersion assessmentVersionOf(RefAssVersion refAssVersion) {
        return Optional.ofNullable(refAssVersion).map(
                version -> AssessmentVersion.builder()
                        // TODO
                        .build()
        ).orElse(null);
    }

}

