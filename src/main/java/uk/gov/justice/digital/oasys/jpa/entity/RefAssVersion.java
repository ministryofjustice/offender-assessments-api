package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "REF_ASS_VERSION", schema = "EOR", catalog = "")
@IdClass(RefAssVersionPK.class)
public class RefAssVersion {
    private Long refAssVersionUk;
    private String refAssVersionCode;
    private String versionNumber;
    private Time startDate;
    private Time endDate;
    private Long oasysFormVersion;
    private Long oasysScoringAlgVersion;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;

    @Basic
    @Column(name = "REF_ASS_VERSION_UK")
    public Long getRefAssVersionUk() {
        return refAssVersionUk;
    }

    public void setRefAssVersionUk(Long refAssVersionUk) {
        this.refAssVersionUk = refAssVersionUk;
    }

    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    public String getRefAssVersionCode() {
        return refAssVersionCode;
    }

    public void setRefAssVersionCode(String refAssVersionCode) {
        this.refAssVersionCode = refAssVersionCode;
    }

    @Id
    @Column(name = "VERSION_NUMBER")
    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
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
    @Column(name = "OASYS_FORM_VERSION")
    public Long getOasysFormVersion() {
        return oasysFormVersion;
    }

    public void setOasysFormVersion(Long oasysFormVersion) {
        this.oasysFormVersion = oasysFormVersion;
    }

    @Basic
    @Column(name = "OASYS_SCORING_ALG_VERSION")
    public Long getOasysScoringAlgVersion() {
        return oasysScoringAlgVersion;
    }

    public void setOasysScoringAlgVersion(Long oasysScoringAlgVersion) {
        this.oasysScoringAlgVersion = oasysScoringAlgVersion;
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
        RefAssVersion that = (RefAssVersion) o;
        return Objects.equals(refAssVersionUk, that.refAssVersionUk) &&
                Objects.equals(refAssVersionCode, that.refAssVersionCode) &&
                Objects.equals(versionNumber, that.versionNumber) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(oasysFormVersion, that.oasysFormVersion) &&
                Objects.equals(oasysScoringAlgVersion, that.oasysScoringAlgVersion) &&
                Objects.equals(checksum, that.checksum) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(lastupdDate, that.lastupdDate) &&
                Objects.equals(lastupdUser, that.lastupdUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(refAssVersionUk, refAssVersionCode, versionNumber, startDate, endDate, oasysFormVersion, oasysScoringAlgVersion, checksum, createDate, createUser, lastupdDate, lastupdUser);
    }
}
