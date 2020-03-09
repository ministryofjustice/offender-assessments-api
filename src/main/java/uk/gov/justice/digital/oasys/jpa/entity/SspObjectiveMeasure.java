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
@Table(name = "SSP_OBJECTIVE_MEASURE")
public class SspObjectiveMeasure implements Serializable {
    @Id
    @Column(name = "SSP_OBJECTIVE_MEASURE_PK")
    private Long sspObjectiveMeasurePk;
    @Column(name = "OBJECTIVE_STATUS_COMMENTS")
    private String objectiveStatusComments;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "OBJECTIVE_STATUS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OBJECTIVE_STATUS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement objectiveStatus;
    @Column(name = "SSP_OBJECTIVES_IN_SET_PK")
    private Long sspObjectivesInSetPk;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
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
