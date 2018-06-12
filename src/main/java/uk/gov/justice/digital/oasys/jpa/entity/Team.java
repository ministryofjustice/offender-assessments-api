package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@IdClass(TeamPK.class)
public class Team {
    private Long teamUk;
    private String ctAreaEstCode;
    private String divisionCode;
    private String teamCode;
    private String teamName;
    private String contactNumber;
    private String emailAddress;
    private Time startDate;
    private Time endDate;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;

    @Basic
    @Column(name = "TEAM_UK")
    public Long getTeamUk() {
        return teamUk;
    }

    public void setTeamUk(Long teamUk) {
        this.teamUk = teamUk;
    }

    @Id
    @Column(name = "CT_AREA_EST_CODE")
    public String getCtAreaEstCode() {
        return ctAreaEstCode;
    }

    public void setCtAreaEstCode(String ctAreaEstCode) {
        this.ctAreaEstCode = ctAreaEstCode;
    }

    @Id
    @Column(name = "DIVISION_CODE")
    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    @Id
    @Column(name = "TEAM_CODE")
    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    @Basic
    @Column(name = "TEAM_NAME")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "CONTACT_NUMBER")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Basic
    @Column(name = "EMAIL_ADDRESS")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "START_DATE")
    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "CHECKSUM")
    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Time getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Time createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_USER")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "LASTUPD_DATE")
    public Time getLastupdDate() {
        return lastupdDate;
    }

    public void setLastupdDate(Time lastupdDate) {
        this.lastupdDate = lastupdDate;
    }

    @Basic
    @Column(name = "LASTUPD_USER")
    public String getLastupdUser() {
        return lastupdUser;
    }

    public void setLastupdUser(String lastupdUser) {
        this.lastupdUser = lastupdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamUk, team.teamUk) &&
                Objects.equals(ctAreaEstCode, team.ctAreaEstCode) &&
                Objects.equals(divisionCode, team.divisionCode) &&
                Objects.equals(teamCode, team.teamCode) &&
                Objects.equals(teamName, team.teamName) &&
                Objects.equals(contactNumber, team.contactNumber) &&
                Objects.equals(emailAddress, team.emailAddress) &&
                Objects.equals(startDate, team.startDate) &&
                Objects.equals(endDate, team.endDate) &&
                Objects.equals(checksum, team.checksum) &&
                Objects.equals(createDate, team.createDate) &&
                Objects.equals(createUser, team.createUser) &&
                Objects.equals(lastupdDate, team.lastupdDate) &&
                Objects.equals(lastupdUser, team.lastupdUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teamUk, ctAreaEstCode, divisionCode, teamCode, teamName, contactNumber, emailAddress, startDate, endDate, checksum, createDate, createUser, lastupdDate, lastupdUser);
    }
}
