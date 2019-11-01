package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "OASYS_USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "PASSWORD_ENCRYPTED")
    private String passwordEncrypted;
    @Column(name = "PASSWORD_CHANGE_DATE")
    private Time passwordChangeDate;
    @Column(name = "LAST_LOGIN")
    private Time lastLogin;
    @Column(name = "FAILED_LOGIN_ATTEMPTS")
    private Long failedLoginAttempts;
    @Column(name = "SYSTEM_IND")
    private String systemInd;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "USER_STATUS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "USER_STATUS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement userStatus;
    @Column(name = "DATE_OF_BIRTH")
    private Time dateOfBirth;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "LEGACY_USER_CODE")
    private String legacyUserCode;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "MIGRATION_SOURCE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "MIGRATION_SOURCE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement migrationSource;
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
    @OneToOne
    @JoinColumn(name = "CT_AREA_EST_CODE")
    private CtAreaEst ctAreaEst;
    @Column(name = "EXCL_DEACT_IND")
    private String exclDeactInd;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "OASYS_USER_CODE", referencedColumnName = "OASYS_USER_CODE")
    private List<AreaEstUserRole> roles;


}
