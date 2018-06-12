package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamPK implements Serializable {
    private String ctAreaEstCode;
    private String divisionCode;
    private String teamCode;

    @Column(name = "CT_AREA_EST_CODE")
    @Id
    public String getCtAreaEstCode() {
        return ctAreaEstCode;
    }

    public void setCtAreaEstCode(String ctAreaEstCode) {
        this.ctAreaEstCode = ctAreaEstCode;
    }

    @Column(name = "DIVISION_CODE")
    @Id
    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    @Column(name = "TEAM_CODE")
    @Id
    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPK teamPK = (TeamPK) o;
        return Objects.equals(ctAreaEstCode, teamPK.ctAreaEstCode) &&
                Objects.equals(divisionCode, teamPK.divisionCode) &&
                Objects.equals(teamCode, teamPK.teamCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ctAreaEstCode, divisionCode, teamCode);
    }
}
