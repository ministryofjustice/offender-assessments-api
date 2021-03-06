package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SSP_INTERVENTION_IN_SET")
public class SspInterventionInSet {
    @Id
    @Column(name = "SSP_INTERVENTION_IN_SET_PK")
    private Long sspInterventionInSetPk;
    @Column(name = "COPIED_FORWARD_INDICATOR")
    private String copiedForwardIndicator;
    @Column(name = "INTERVENTION_COMMENT")
    private String interventionComment;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TIMESCALE_FOR_INT_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "TIMESCALE_FOR_INT_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement timescaleForIntervention;

    @Column(name = "COPIED_FROM_SSP_INT_IN_SET")
    private Long copiedFromSspIntInSet;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "INTERVENTION_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "INTERVENTION_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement intervention;

    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CF_LAST_ASSMT_INT")
    private Long cfLastAssmtInt;
    @Column(name = "CF_ORIG_ASSMT_INT")
    private Long cfOrigAssmtInt;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Time createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

    @OneToMany
    @JoinColumn(name = "SSP_INTERVENTION_IN_SET_PK", referencedColumnName = "SSP_INTERVENTION_IN_SET_PK", insertable = false, updatable = false)
    private Set<SspWhoDoWorkPivot> sspWhoDoWorkPivot;

    @ManyToOne
    @JoinColumn(name = "SSP_INTERVENTION_IN_SET_PK", referencedColumnName = "SSP_INTERVENTION_IN_SET_PK",insertable=false, updatable=false)
    private SspInterventionMeasure sspInterventionMeasure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspInterventionInSet))
            return false;

        SspInterventionInSet other = (SspInterventionInSet) o;
        return getSspInterventionInSetPk() != null &&
                getSspInterventionInSetPk().equals(other.getSspInterventionInSetPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
