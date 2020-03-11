package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Data
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

}
