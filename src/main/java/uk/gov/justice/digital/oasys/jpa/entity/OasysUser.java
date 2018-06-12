package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "OASYS_USER", schema = "EOR", catalog = "")
public class OasysUser {
    private Long oasysUserUk;
    private String oasysUserCode;
    private String userForename1;
    private String userForename2;
    private String userForename3;
    private String userFamilyName;
    private byte[] passwordEncrypted;
    private Time passwordChangeDate;
    private Time lastLogin;
    private Long failedLoginAttempts;
    private String systemInd;
    private Time dateOfBirth;
    private String password;
    private String emailAddress;
    private String legacyUserCode;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;
    private String exclDeactInd;

    @Basic
    @Column(name = "OASYS_USER_UK")
    public Long getOasysUserUk() {
        return oasysUserUk;
    }

    public void setOasysUserUk(Long oasysUserUk) {
        this.oasysUserUk = oasysUserUk;
    }

    @Id
    @Column(name = "OASYS_USER_CODE")
    public String getOasysUserCode() {
        return oasysUserCode;
    }

    public void setOasysUserCode(String oasysUserCode) {
        this.oasysUserCode = oasysUserCode;
    }

    @Basic
    @Column(name = "USER_FORENAME_1")
    public String getUserForename1() {
        return userForename1;
    }

    public void setUserForename1(String userForename1) {
        this.userForename1 = userForename1;
    }

    @Basic
    @Column(name = "USER_FORENAME_2")
    public String getUserForename2() {
        return userForename2;
    }

    public void setUserForename2(String userForename2) {
        this.userForename2 = userForename2;
    }

    @Basic
    @Column(name = "USER_FORENAME_3")
    public String getUserForename3() {
        return userForename3;
    }

    public void setUserForename3(String userForename3) {
        this.userForename3 = userForename3;
    }

    @Basic
    @Column(name = "USER_FAMILY_NAME")
    public String getUserFamilyName() {
        return userFamilyName;
    }

    public void setUserFamilyName(String userFamilyName) {
        this.userFamilyName = userFamilyName;
    }

    @Basic
    @Column(name = "PASSWORD_ENCRYPTED")
    public byte[] getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(byte[] passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    @Basic
    @Column(name = "PASSWORD_CHANGE_DATE")
    public Time getPasswordChangeDate() {
        return passwordChangeDate;
    }

    public void setPasswordChangeDate(Time passwordChangeDate) {
        this.passwordChangeDate = passwordChangeDate;
    }

    @Basic
    @Column(name = "LAST_LOGIN")
    public Time getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Time lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    public Long getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Long failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    @Basic
    @Column(name = "SYSTEM_IND")
    public String getSystemInd() {
        return systemInd;
    }

    public void setSystemInd(String systemInd) {
        this.systemInd = systemInd;
    }

    @Basic
    @Column(name = "DATE_OF_BIRTH")
    public Time getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Time dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "LEGACY_USER_CODE")
    public String getLegacyUserCode() {
        return legacyUserCode;
    }

    public void setLegacyUserCode(String legacyUserCode) {
        this.legacyUserCode = legacyUserCode;
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

    @Basic
    @Column(name = "EXCL_DEACT_IND")
    public String getExclDeactInd() {
        return exclDeactInd;
    }

    public void setExclDeactInd(String exclDeactInd) {
        this.exclDeactInd = exclDeactInd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OasysUser oasysUser = (OasysUser) o;
        return Objects.equals(oasysUserUk, oasysUser.oasysUserUk) &&
                Objects.equals(oasysUserCode, oasysUser.oasysUserCode) &&
                Objects.equals(userForename1, oasysUser.userForename1) &&
                Objects.equals(userForename2, oasysUser.userForename2) &&
                Objects.equals(userForename3, oasysUser.userForename3) &&
                Objects.equals(userFamilyName, oasysUser.userFamilyName) &&
                Arrays.equals(passwordEncrypted, oasysUser.passwordEncrypted) &&
                Objects.equals(passwordChangeDate, oasysUser.passwordChangeDate) &&
                Objects.equals(lastLogin, oasysUser.lastLogin) &&
                Objects.equals(failedLoginAttempts, oasysUser.failedLoginAttempts) &&
                Objects.equals(systemInd, oasysUser.systemInd) &&
                Objects.equals(dateOfBirth, oasysUser.dateOfBirth) &&
                Objects.equals(password, oasysUser.password) &&
                Objects.equals(emailAddress, oasysUser.emailAddress) &&
                Objects.equals(legacyUserCode, oasysUser.legacyUserCode) &&
                Objects.equals(checksum, oasysUser.checksum) &&
                Objects.equals(createDate, oasysUser.createDate) &&
                Objects.equals(createUser, oasysUser.createUser) &&
                Objects.equals(lastupdDate, oasysUser.lastupdDate) &&
                Objects.equals(lastupdUser, oasysUser.lastupdUser) &&
                Objects.equals(exclDeactInd, oasysUser.exclDeactInd);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(oasysUserUk, oasysUserCode, userForename1, userForename2, userForename3, userFamilyName, passwordChangeDate, lastLogin, failedLoginAttempts, systemInd, dateOfBirth, password, emailAddress, legacyUserCode, checksum, createDate, createUser, lastupdDate, lastupdUser, exclDeactInd);
        result = 31 * result + Arrays.hashCode(passwordEncrypted);
        return result;
    }
}
