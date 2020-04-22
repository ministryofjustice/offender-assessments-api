package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamPK implements Serializable {
    @Column(name = "CT_AREA_EST_CODE")
    @Id
    private String ctAreaEstCode;
    @Column(name = "DIVISION_CODE")
    @Id
    private String divisionCode;
    @Column(name = "TEAM_CODE")
    @Id
    private String teamCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamPK)) return false;
        TeamPK that = (TeamPK) o;
        return Objects.nonNull(getCtAreaEstCode()) &&
                Objects.nonNull(getDivisionCode()) &&
                Objects.nonNull(getTeamCode()) &&
                Objects.equals(getCtAreaEstCode(), that.getCtAreaEstCode()) &&
                Objects.equals(getDivisionCode(), that.getDivisionCode()) &&
                Objects.equals(getTeamCode(), that.getTeamCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
