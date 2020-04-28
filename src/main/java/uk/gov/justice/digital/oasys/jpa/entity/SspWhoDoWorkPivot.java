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
@Table(name = "SSP_WHO_DO_WORK_PIVOT")
public class SspWhoDoWorkPivot implements Serializable {
    @Id
    @Column(name = "SSP_WHO_DO_WORK_PIVOT_PK")
    private Long sspWhoDoWorkPivotPk;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "WHO_WORK_OBJ_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "WHO_WORK_OBJ_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement whoDoWork;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "SSP_INTERVENTION_IN_SET_PK")
    private Long sspInterventionInSetPk;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspWhoDoWorkPivot))
            return false;

        SspWhoDoWorkPivot other = (SspWhoDoWorkPivot) o;
        return getSspWhoDoWorkPivotPk() != null &&
                getSspWhoDoWorkPivotPk().equals(other.getSspWhoDoWorkPivotPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
