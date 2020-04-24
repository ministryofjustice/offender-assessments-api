package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Getter
@Entity
@Table(name = "COURT")
public class Court {
    @Id
    @Column(name = "COURT_PK")
    private Long courtPk;
    @Column(name = "COURT_CODE")
    private String courtCode;
    @Column(name = "COURT_NAME")
    private String courtName;
    @Column(name = "START_DATE")
    private Time startDate;
    @Column(name = "END_DATE")
    private Time endDate;
    @Column(name = "GENERAL_CODE")
    private String generalCode;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Time createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Court))
            return false;

        Court other = (Court) o;
        return getCourtPk() != null &&
                getCourtPk().equals(other.getCourtPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
