package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Builder
@Entity
@Getter
@Table(name = "SSP_OBJ_INTERVENE_PIVOT")
@NoArgsConstructor
@AllArgsConstructor
public class SspObjIntervenePivot {
    @Id
    @Column(name = "SSP_OBJ_INTERVENE_PIVOT_PK")
    private Long sspObjIntervenePivotPk;
    @Column(name = "SSP_OBJECTIVES_IN_SET_PK")
    private Long sspObjectivesInSetPk;
    @Column(name = "DELETED_IND")
    private String deletedInd;
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

    @OneToOne
    @JoinColumn(name = "SSP_INTERVENTION_IN_SET_PK", referencedColumnName = "SSP_INTERVENTION_IN_SET_PK")
    private SspInterventionInSet sspInterventionInSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspObjIntervenePivot))
            return false;

        SspObjIntervenePivot other = (SspObjIntervenePivot) o;
        return getSspObjIntervenePivotPk() != null &&
                getSspObjIntervenePivotPk().equals(other.getSspObjIntervenePivotPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
