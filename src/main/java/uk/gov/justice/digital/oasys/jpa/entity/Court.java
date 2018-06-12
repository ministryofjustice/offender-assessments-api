package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Court {
    private Long courtPk;
    private String courtCode;
    private String courtName;
    private Time startDate;
    private Time endDate;
    private String generalCode;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;

    @Id
    @Column(name = "COURT_PK")
    public Long getCourtPk() {
        return courtPk;
    }

    public void setCourtPk(Long courtPk) {
        this.courtPk = courtPk;
    }

    @Basic
    @Column(name = "COURT_CODE")
    public String getCourtCode() {
        return courtCode;
    }

    public void setCourtCode(String courtCode) {
        this.courtCode = courtCode;
    }

    @Basic
    @Column(name = "COURT_NAME")
    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
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
    @Column(name = "GENERAL_CODE")
    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
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
        Court court = (Court) o;
        return Objects.equals(courtPk, court.courtPk) &&
                Objects.equals(courtCode, court.courtCode) &&
                Objects.equals(courtName, court.courtName) &&
                Objects.equals(startDate, court.startDate) &&
                Objects.equals(endDate, court.endDate) &&
                Objects.equals(generalCode, court.generalCode) &&
                Objects.equals(checksum, court.checksum) &&
                Objects.equals(createDate, court.createDate) &&
                Objects.equals(createUser, court.createUser) &&
                Objects.equals(lastupdDate, court.lastupdDate) &&
                Objects.equals(lastupdUser, court.lastupdUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(courtPk, courtCode, courtName, startDate, endDate, generalCode, checksum, createDate, createUser, lastupdDate, lastupdUser);
    }
}
