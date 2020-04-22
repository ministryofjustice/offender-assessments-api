package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SSP_INTERVENTION_MEASURE")
public class SspInterventionMeasure implements Serializable {
    @Id
    @Column(name = "SSP_INTERVENTION_MEASURE_PK")
    private Long sspInterventionMeasurePk;
    @Column(name = "INTERVENE_MEAS_COMMENT")
    private String InterventionStatusComments;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "INTERVENE_MEAS_CODE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "INTERVENE_MEAS_CODE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement interventionStatus;
    @Column(name = "SSP_INTERVENTION_IN_SET_PK")
    private Long sspInterventionsInSetPk;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspInterventionMeasure))
            return false;

        SspInterventionMeasure other = (SspInterventionMeasure) o;
        return getSspInterventionsInSetPk() != null &&
                getSspInterventionsInSetPk().equals(other.getSspInterventionsInSetPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
