package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SSP_OBJECTIVE")
@Builder
public class SspObjective implements Serializable {
    @Id
    @Column(name = "SSP_OBJECTIVE_PK")
    private Long sspObjectivePk;
    @Column(name = "OBJECTIVE_DESC")
    private String objectiveDesc;
    @Column(name = "SSP_OBJECTIVES_IN_SET_PK")
    private Long sspObjectivesInSetPk;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

    @ManyToOne
    @JoinColumn(name = "OBJECTIVE_CODE")
    private Objective objective;


}
