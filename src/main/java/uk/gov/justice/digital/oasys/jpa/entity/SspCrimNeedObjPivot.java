package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SSP_CRIM_NEED_OBJ_PIVOT")
public class SspCrimNeedObjPivot {
    @Id
    @Column(name = "SSP_CRIM_NEED_OBJ_PIVOT_PK")
    private Long sspCrimNeedObjPivotPk;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "CRIMINOGENIC_NEED_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "CRIMINOGENIC_NEED_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement criminogenicNeed;

    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspCrimNeedObjPivot))
            return false;

        SspCrimNeedObjPivot other = (SspCrimNeedObjPivot) o;
        return getSspCrimNeedObjPivotPk() != null &&
                getSspCrimNeedObjPivotPk().equals(other.getSspCrimNeedObjPivotPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
