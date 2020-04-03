package uk.gov.justice.digital.oasys.jpa.entity.simple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;
import uk.gov.justice.digital.oasys.jpa.entity.OasysBcsPart;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "OASYS_SET")
@Builder
public class Assessment {
    @Id
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "ASSESSOR_NAME")
    private String assessorName;
    @Column(name = "ASSESSMENT_STATUS_ELM")
    private String assessmentStatus;
    @Column(name = "ASSESSMENT_TYPE_ELM")
    private String assessmentType;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "DATE_COMPLETED")
    private LocalDateTime dateCompleted;
    @Column(name = "ASSESSMENT_VOIDED_DATE")
    private LocalDateTime assessmentVoidedDate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER")
    })
    private RefAssessmentVersion assessmentVersion;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Collection<Section> oasysSections;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Collection<OasysBcsPart> oasysBcsParts;

    @ManyToOne
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK")
    private AssessmentGroup group;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Set<BasicSentencePlanObj> basicSentencePlanList;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Set<SspObjectivesInSet> sspObjectivesInSets;

}