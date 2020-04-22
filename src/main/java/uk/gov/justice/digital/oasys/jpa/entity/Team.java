package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TEAM")
@IdClass(TeamPK.class)
public class Team {
    @Id
    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Id
    @Column(name = "DIVISION_CODE")
    private String divisionCode;
    @Id
    @Column(name = "TEAM_CODE")
    private String teamCode;
    @Column(name = "TEAM_UK")
    private Long teamUk;
    @Column(name = "TEAM_NAME")
    private String teamName;
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
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
