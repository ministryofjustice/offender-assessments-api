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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SspObjectiveMeasure))
            return false;

        SspObjectiveMeasure other = (SspObjectiveMeasure) o;
        return getSspObjectiveMeasurePk() != null &&
                getSspObjectiveMeasurePk().equals(other.getSspObjectiveMeasurePk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
