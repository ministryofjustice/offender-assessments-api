package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "OASYS_USER")
public class OasysUser {
    @Column(name = "OASYS_USER_UK")
    private Long oasysUserUk;
    @Id
    @Column(name = "OASYS_USER_CODE")
    private String oasysUserCode;
    @Column(name = "USER_FORENAME_1")
    private String userForename1;
    @Column(name = "USER_FORENAME_2")
    private String userForename2;
    @Column(name = "USER_FORENAME_3")
    private String userForename3;
    @Column(name = "USER_FAMILY_NAME")
    private String userFamilyName;
//    @Column(name = "PASSWORD_ENCRYPTED")
//    private byte[] passwordEncrypted;
    @Column(name = "PASSWORD_CHANGE_DATE")
    private Time passwordChangeDate;
    @Column(name = "LAST_LOGIN")
    private Time lastLogin;
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    private Long failedLoginAttempts;
    @Column(name = "SYSTEM_IND")
    private String systemInd;
    @Column(name = "DATE_OF_BIRTH")
    private Time dateOfBirth;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "LEGACY_USER_CODE")
    private String legacyUserCode;
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
    @Column(name = "EXCL_DEACT_IND")
    private String exclDeactInd;

}
