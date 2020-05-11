package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Column(name = "OGP_ST_WESC")
    private BigDecimal ogpStWesc;
    @Column(name = "OGP_DY_WESC")
    private BigDecimal ogpDyWesc;
    @Column(name = "OGP_TOT_WESC")
    private BigDecimal ogpTotWesc;
    @Column(name = "OVP_ST_WESC")
    private BigDecimal ovpStWesc;
    @Column(name = "OVP_DY_WESC")
    private BigDecimal ovpDyWesc;
    @Column(name = "OVP_TOT_WESC")
    private BigDecimal ovpTotWesc;
    @Column(name = "OGRS3_1YEAR")
    private BigDecimal ogrs31Year;
    @Column(name = "OGRS3_2YEAR")
    private BigDecimal ogrs32Year;
    @Column(name = "OGP_1YEAR")
    private BigDecimal ogp1Year;
    @Column(name = "OGP_2YEAR")
    private BigDecimal ogp2Year;
    @Column(name = "OVP_1YEAR")
    private BigDecimal ovp1Year;
    @Column(name = "OVP_2YEAR")
    private BigDecimal ovp2Year;
    @Column(name = "OVP_PREV_WESC")
    private BigDecimal ovpPrevWesc;
    @Column(name = "OVP_VIO_WESC")
    private BigDecimal ovpVioWesc;
    @Column(name = "OVP_NON_VIO_WESC")
    private BigDecimal ovpNonVioWesc;
    @Column(name = "OVP_AGE_WESC")
    private BigDecimal ovpAgeWesc;
    @Column(name = "OVP_SEX_WESC")
    private BigDecimal ovpSexWesc;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER")
    })
    private RefAssessmentVersion assessmentVersion;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Collection<Section> oasysSections;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OGP_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OGP_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ogpRiskRecon;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OGRS3_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OGRS3_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ogrs3RiskRecon;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OVP_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OVP_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ovpRiskRecon;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OTHER_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OTHER_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement otherRiskRecon;

    @ManyToOne
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK")
    private AssessmentGroup group;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Collection<OasysBcsPart> oasysBcsParts;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Set<BasicSentencePlanObj> basicSentencePlanList;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Set<SspObjectivesInSet> sspObjectivesInSets;

    //TODO: Sentence data shuold come from Delius or Nomis
    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private Set<OffenceBlock> offenceBlocks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Assessment))
            return false;

        Assessment other = (Assessment) o;
        return oasysSetPk != null &&
                oasysSetPk.equals(other.getOasysSetPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}