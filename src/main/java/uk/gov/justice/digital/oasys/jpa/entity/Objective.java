package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@Getter
@Entity
@Builder
@Table(name = "OBJECTIVE")
@NoArgsConstructor
@AllArgsConstructor
public class Objective {
    @Column(name = "OBJECTIVE_UK")
    private Long objectiveUk;
    @Id
    @Column(name = "OBJECTIVE_CODE")
    private String objectiveCode;
    @Column(name = "OBJECTIVE_DESC")
    private String objectiveDesc;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OBJECTIVE_HEADING_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OBJECTIVE_HEADING_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement objectiveHeading;

    @Column(name = "START_DATE")
    private Time startDate;
    @Column(name = "END_DATE")
    private Time endDate;
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

        if (!(o instanceof Objective))
            return false;

        Objective other = (Objective) o;
        return getObjectiveCode() != null &&
                getObjectiveCode().equals(other.getObjectiveCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
