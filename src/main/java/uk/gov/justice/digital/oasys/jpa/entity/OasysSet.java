package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@ToString(exclude = "group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OASYS_SET")
public class OasysSet {
    @Id
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "ADDL_OFFC_V2_LGCYDAT")
    private String addlOffcV2Lgcydat;
    @Column(name = "APPEAL_PENDING_DETAILS")
    private String appealPendingDetails;
    @Column(name = "APPEAL_PENDING_INDICATOR")
    private String appealPendingIndicator;
    @Column(name = "ASSESSMENT_VOIDED_BY")
    private String assessmentVoidedBy;
    @Column(name = "ASSESSMENT_VOIDED_DATE")
    private Timestamp assessmentVoidedDate;
    @Column(name = "ASSESSOR_NAME")
    private String assessorName;
    @Column(name = "ASSESSOR_OFFICE")
    private String assessorOffice;
    @Column(name = "ASSESSOR_TEAM")
    private String assessorTeam;
    @Column(name = "ASSESSOR_PHONE_NUMBER")
    private String assessorPhoneNumber;
    @Column(name = "ASSESSOR_EMAIL")
    private String assessorEmail;
    @Column(name = "ASSESSOR_POSITION")
    private String assessorPosition;
    @Column(name = "ASSESSOR_SIGNED_DATE")
    private Time assessorSignedDate;
    @Column(name = "AUTOMATIC_RELEASE_DATE")
    private Time automaticReleaseDate;
    @Column(name = "CHARGES_PENDING_DTL")
    private String chargesPendingDtl;
    @Column(name = "CHARGES_PENDING_IND")
    private String chargesPendingInd;
    @Column(name = "CONDITIONAL_RELEASE_DATE")
    private Time conditionalReleaseDate;
    @Column(name = "COUNTERSIGNER_NAME")
    private String countersignerName;
    @Column(name = "COUNTERSIGNER_OFFICE")
    private String countersignerOffice;
    @Column(name = "COUNTERSIGNER_PHONE_NUM")
    private String countersignerPhoneNum;
    @Column(name = "COUNTERSIGNER_POSITION")
    private String countersignerPosition;
    @Column(name = "COUNTERSIGNER_SIGNED_DATE")
    private Time countersignerSignedDate;
    @Column(name = "COUNTERSIGNER_COMMENTS")
    private String countersignerComments;
    @Column(name = "CMS_PROB_NUMBER")
    private String cmsProbNumber;
    @Column(name = "CMS_PRIS_NUMBER")
    private String cmsPrisNumber;
    @Column(name = "LEGACY_CMS_PROB_NUMBER")
    private String legacyCmsProbNumber;
    @Column(name = "CRO_NUMBER")
    private String croNumber;
    @Column(name = "CURRENT_ADDRESS_LINE_1")
    private String currentAddressLine1;
    @Column(name = "CURRENT_ADDRESS_LINE_2")
    private String currentAddressLine2;
    @Column(name = "CURRENT_ADDRESS_LINE_3")
    private String currentAddressLine3;
    @Column(name = "CURRENT_ADDRESS_LINE_4")
    private String currentAddressLine4;
    @Column(name = "CURRENT_ADDRESS_LINE_5")
    private String currentAddressLine5;
    @Column(name = "CURRENT_ADDRESS_LINE_6")
    private String currentAddressLine6;
    @Column(name = "CURRENT_POST_CODE")
    private String currentPostCode;
    @Column(name = "CURRENT_TELEPHONE_NUMBER")
    private String currentTelephoneNumber;
    @Column(name = "DATE_OF_ACTUAL_RELEASE")
    private Time dateOfActualRelease;
    @Column(name = "DATE_OF_BIRTH")
    private Time dateOfBirth;
    @Column(name = "DET_U_IMMIGRATION_ACT_IND")
    private String detUImmigrationActInd;
    @Column(name = "DISCHARGE_ADDRESS_LINE_1")
    private String dischargeAddressLine1;
    @Column(name = "DISCHARGE_ADDRESS_LINE_2")
    private String dischargeAddressLine2;
    @Column(name = "DISCHARGE_ADDRESS_LINE_3")
    private String dischargeAddressLine3;
    @Column(name = "DISCHARGE_ADDRESS_LINE_4")
    private String dischargeAddressLine4;
    @Column(name = "DISCHARGE_ADDRESS_LINE_5")
    private String dischargeAddressLine5;
    @Column(name = "DISCHARGE_ADDRESS_LINE_6")
    private String dischargeAddressLine6;
    @Column(name = "DISCHARGE_POST_CODE")
    private String dischargePostCode;
    @Column(name = "DISCHARGE_TELEPHONE_NUM")
    private String dischargeTelephoneNum;
    @Column(name = "FACILITY_LIC_ELIG_DATE")
    private Time facilityLicEligDate;
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "FORENAME_1")
    private String forename1;
    @Column(name = "FORENAME_2")
    private String forename2;
    @Column(name = "FORENAME_3")
    private String forename3;
    @Column(name = "HOME_DETN_CURFEW_DATE")
    private Time homeDetnCurfewDate;
    @Column(name = "INITIATION_DATE")
    private Time initiationDate;
    @Column(name = "INTERPRETER_REQUIRED_IND")
    private String interpreterRequiredInd;
    @Column(name = "LICENCE_EXPIRY_DATE")
    private Time licenceExpiryDate;
    @Column(name = "LICENCE_REQUIRE_RELEASE")
    private String licenceRequireRelease;
    @Column(name = "NON_PAROLE_DATE")
    private Time nonParoleDate;
    @Column(name = "OASYS_FORM_VERSION")
    private Long oasysFormVersion;
    @Column(name = "OASYS_SCORING_ALG_VERSION")
    private Long oasysScoringAlgVersion;
    @Column(name = "OFFDR_IDENT_PERSIST_IND")
    private String offdrIdentPersistInd;
    @Column(name = "PAROLE_ELIGIBILITY_DATE")
    private Time paroleEligibilityDate;
    @Column(name = "PNC")
    private String pnc;
    @Column(name = "PRF_SPKN_LNG_OFTXT")
    private String prfSpknLngOftxt;
    @Column(name = "PRF_WRT_LNG_OFTXT")
    private String prfWrtLngOftxt;
    @Column(name = "PRISON_NUMBER")
    private String prisonNumber;
    @Column(name = "PSR_CLOSED_GROUP_IND")
    private String psrClosedGroupInd;
    @Column(name = "PURPOSE_ASSMT_OTHER_FTXT")
    private String purposeAssmtOtherFtxt;
    @Column(name = "REASON_FOR_VOIDING")
    private String reasonForVoiding;
    @Column(name = "RECOMMENDED_DEP_IND")
    private String recommendedDepInd;
    @Column(name = "RELIGION_OTHER")
    private String religionOther;
    @Column(name = "REQUESTED_BY")
    private String requestedBy;
    @Column(name = "DATE_COMPLETED")
    private Timestamp dateCompleted;
    @Column(name = "DATE_ASSESSMENT_REQST")
    private Time dateAssessmentReqst;
    @Column(name = "DATE_REPORT_REQST")
    private Time dateReportReqst;
    @Column(name = "RESETTLE_LIC_ELIG_DATE")
    private Time resettleLicEligDate;
    @Column(name = "SENTENCE_EXPIRY_DATE")
    private Time sentenceExpiryDate;
    @Column(name = "SOURCES_INFO_OTHER_FTXT")
    private String sourcesInfoOtherFtxt;
    @Column(name = "LOOKED_AFTER")
    private String lookedAfter;
    @Column(name = "SECURITY_CATEGORY")
    private String securityCategory;
    @Column(name = "CELL_LOCATION")
    private String cellLocation;
    @Column(name = "OGP_ST_WESC")
    private BigDecimal ogpStWesc;
    @Column(name = "OGP_DY_WESC")
    private BigDecimal ogpDyWesc;
    @Column(name = "OGP_TOT_WESC")
    private BigDecimal ogpTotWesc;
    @Column(name = "OVP_ST_WESC")
    private BigDecimal ovpStWesc;
    @Column(name = "OVP_DY_WESC")
    private BigDecimal ovpDyWesc;
    @Column(name = "OVP_TOT_WESC")
    private BigDecimal ovpTotWesc;
    @Column(name = "OGRS3_1YEAR")
    private BigDecimal ogrs31Year;
    @Column(name = "OGRS3_2YEAR")
    private BigDecimal ogrs32Year;
    @Column(name = "OGP_1YEAR")
    private BigDecimal ogp1Year;
    @Column(name = "OGP_2YEAR")
    private BigDecimal ogp2Year;
    @Column(name = "OVP_1YEAR")
    private BigDecimal ovp1Year;
    @Column(name = "OVP_2YEAR")
    private BigDecimal ovp2Year;
    @Column(name = "OVP_PREV_WESC")
    private BigDecimal ovpPrevWesc;
    @Column(name = "OVP_VIO_WESC")
    private BigDecimal ovpVioWesc;
    @Column(name = "OVP_NON_VIO_WESC")
    private BigDecimal ovpNonVioWesc;
    @Column(name = "OVP_AGE_WESC")
    private BigDecimal ovpAgeWesc;
    @Column(name = "OVP_SEX_WESC")
    private BigDecimal ovpSexWesc;
    @Column(name = "LOW_SCORE_NEED_ATTN_REASON")
    private String lowScoreNeedAttnReason;
    @Column(name = "COURT_OTHER_TEXT")
    private String courtOtherText;
    @Column(name = "RECALL_DATE")
    private Time recallDate;
    @Column(name = "DELETED_DATE")
    private Time deletedDate;
    @Column(name = "BCS_SENT_PLAN_TEXT")
    private String bcsSentPlanText;
    @Column(name = "ARMED_FORCES_IND")
    private String armedForcesInd;
    @Column(name = "NO_SARA_DATE")
    private Time noSaraDate;
    @Column(name = "NO_RM2000_DATE")
    private Time noRm2000Date;
    @Column(name = "PHYSICAL_LOCATION_OTHER")
    private String physicalLocationOther;
    @Column(name = "INVALID_SECT1_SCORE")
    private String invalidSect1Score;
    @Column(name = "AGE_FOLLOW_UP")
    private Long ageFollowUp;
    @Column(name = "CMS_LAST_EXPORT_DATE")
    private Time cmsLastExportDate;
    @Column(name = "CMS_RESEND_IND")
    private String cmsResendInd;
    @Column(name = "CLONED_LOCKED_INCOMP_IND")
    private String clonedLockedIncompInd;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;
    @Column(name = "CMS_EVENT_NUMBER")
    private Long cmsEventNumber;
    @Column(name = "NHS_NUMBER")
    private String nhsNumber;
    @Column(name = "NI_NUMBER")
    private String niNumber;
    @Column(name = "POST_SENT_SUPV_DATE_IND")
    private String postSentSupvDateInd;
    @Column(name = "POST_SENT_SUPV_DATE")
    private Time postSentSupvDate;
    @Column(name = "BCS_SYSTEM_CREATED_IND")
    private String bcsSystemCreatedInd;
    @Column(name = "RECEPTION_DATE")
    private Time receptionDate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ASSESSMENT_STATUS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ASSESSMENT_STATUS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement assessmentStatus;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ASSESSMENT_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ASSESSMENT_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })

    private RefElement assessmentType;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ASSESSOR_SERVICE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ASSESSOR_SERVICE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement assessorService;

    @OneToOne
    @JoinColumn(name = "ASSESSOR_USER", referencedColumnName = "OASYS_USER_CODE")
    private OasysUser assessorUser;
    @OneToOne

    @JoinColumns({
            @JoinColumn(name = "BCS_CARED_FOR_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "BCS_CARED_FOR_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement bcsCaredFor;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "COUNTERSIGNER_SERVICE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "COUNTERSIGNER_SERVICE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement countersignerService;

    @OneToOne
    @JoinColumn(name = "COUNTERSIGNER_USER", referencedColumnName = "OASYS_USER_CODE")
    private OasysUser countersignerUser;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "CURRENT_LOCAL_AUTHORITY_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "CURRENT_LOCAL_AUTHORITY_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement currentLocalAuthority;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "DELETE_PSR_TEMPLATE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "DELETE_PSR_TEMPLATE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement deletePsrTemplate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "DISCHARGE_LOCAL_AUTHORITY_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "DISCHARGE_LOCAL_AUTHORITY_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement dischargeLocalAuthority;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ETHNIC_CATEGORY_CODE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ETHNIC_CATEGORY_CODE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ethnicCategoryCode;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "GENDER_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "GENDER_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement gender;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "LEVEL_HEALTHCARE_REQ_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "LEVEL_HEALTHCARE_REQ_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement levelHealthcareReq;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "NO_RM2000_REASON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "NO_RM2000_REASON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement noRm2000Reason;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "NO_SARA_REASON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "NO_SARA_REASON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement noSaraReason;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OGP_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OGP_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ogpRiskRecon;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OGRS3_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OGRS3_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
        private RefElement ogrs3RiskRecon;

    @OneToOne
    @JoinColumn(name = "ORIGINATING_AREAEST_CODE", referencedColumnName = "CT_AREA_EST_CODE", insertable = false, updatable = false)
    private CtAreaEst originatingAreaEst;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ORIGINATING_AREAEST_CODE", referencedColumnName = "CT_AREA_EST_CODE"),
            @JoinColumn(name = "ORIGINATING_DIVISION_CODE", referencedColumnName = "DIVISION_CODE"),
            @JoinColumn(name = "ORIGINATING_TEAM_CODE", referencedColumnName = "TEAM_CODE")
    })
    private Team originatingTeam;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OTHER_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OTHER_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement otherRiskRecon;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OVP_RISK_RECON_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OVP_RISK_RECON_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ovpRiskRecon;

    @Column(name = "PARENT_OASYS_SET_PK")
    private Long parentOasysSetPk;

    @OneToOne
    @JoinColumn(name = "PHYSICAL_LOCATION", referencedColumnName = "CT_AREA_EST_CODE")
    private CtAreaEst physicalLocation;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "PREF_SPOKEN_LANGUAGE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "PREF_SPOKEN_LANGUAGE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement prefSpokenLanguage;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "PREF_WRITE_LANGUAGE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "PREF_WRITE_LANGUAGE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement prefWriteLanguage;

    @OneToOne
    @JoinColumn(name = "PSR_COURT", referencedColumnName = "COURT_PK")
    private Court psrCourt;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "PSR_TEMPLATE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "PSR_TEMPLATE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement psrTemplate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "PURPOSE_ASSESSMENT_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "PURPOSE_ASSESSMENT_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement purposeAssessment;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER")
    })
    private RefAssVersion refAssVersion;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "RELEASE_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "RELEASE_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement releaseType;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "RELIGION_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "RELIGION_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement religion;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ROSH_LEVEL_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ROSH_LEVEL_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement roshLevel;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "SSP_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "SSP_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement sspType;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "TIER_LEVEL_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "TIER_LEVEL_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement tierLevel;

    @OneToMany
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private List<OasysSection> oasysSections;

    @ManyToOne
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK")
    private OasysAssessmentGroup group;

}
