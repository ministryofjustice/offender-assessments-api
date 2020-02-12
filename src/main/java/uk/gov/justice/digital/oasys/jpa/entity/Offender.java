package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFENDER")
public class Offender {
    @Id
    @Column(name = "OFFENDER_PK")
    private Long offenderPk;
    @Column(name = "ADDRESS_LAST_UPDATE_DATE")
    private LocalDateTime addressLastUpdateDate;
    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;
    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;
    @Column(name = "ADDRESS_LINE_3")
    private String addressLine3;
    @Column(name = "ADDRESS_LINE_4")
    private String addressLine4;
    @Column(name = "ADDRESS_LINE_5")
    private String addressLine5;
    @Column(name = "ADDRESS_LINE_6")
    private String addressLine6;
    @Column(name = "ADDRESS_POSTCODE")
    private String addressPostcode;
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "TITLE_ELM")
    private String titleElm;
    @Column(name = "TITLE_CAT")
    private String titleCat;
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "NAME_SOUNDSLIKE")
    private String nameSoundslike;
    @Column(name = "FORENAME_1")
    private String forename1;
    @Column(name = "FORENAME_2")
    private String forename2;
    @Column(name = "FORENAME_3")
    private String forename3;
    @Column(name = "LIMITED_ACCESS_OFFENDER")
    private String limitedAccessOffender;
    @Column(name = "PNC")
    private String pnc;
    @Column(name = "CMS_PROB_NUMBER")
    private String cmsProbNumber;
    @Column(name = "CMS_PRIS_NUMBER")
    private String cmsPrisNumber;
    @Column(name = "LEGACY_CMS_PROB_NUMBER")
    private String legacyCmsProbNumber;
    @Column(name = "CRO_NUMBER")
    private String croNumber;
    @Column(name = "PRISON_NUMBER")
    private String prisonNumber;
    @Column(name = "MERGE_PNC_NUMBER")
    private String mergePncNumber;
    @Column(name = "DATE_OF_DEATH")
    private LocalDate dateOfDeath;
    @Column(name = "DATE_OF_DEPORTATION")
    private LocalDate dateOfDeportation;
    @Column(name = "OFFENDER_MANAGED_IND")
    private String offenderManagedInd;
    @Column(name = "PPO_IND")
    private String ppoInd;
    @Column(name = "REMAND_IND")
    private String remandInd;
    @Column(name = "REVIEW_DATE")
    private LocalDateTime reviewDate;
    @Column(name = "REVIEW_REMINDER_DATE")
    private LocalDateTime reviewReminderDate;
    @Column(name = "TERM_REMINDER_DATE")
    private LocalDateTime termReminderDate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ETHNIC_CATEGORY_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ETHNIC_CATEGORY_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement ethnicCategory;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "GENDER_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "GENDER_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement gender;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "RISK_TO_OTHERS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "RISK_TO_OTHERS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement riskToOthers;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "RISK_TO_SELF_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "RISK_TO_SELF_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement riskToSelf;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OFFENDER_HISTORIC_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OFFENDER_HISTORIC_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement offenderHistoric;

    @Column(name = "LOCAL_AUTHORITY_ELM")
    private String localAuthorityElm;
    @Column(name = "LOCAL_AUTHORITY_CAT")
    private String localAuthorityCat;
    @Column(name = "ORIGINATING_CMS_ELM")
    private String originatingCmsElm;
    @Column(name = "ORIGINATING_CMS_CAT")
    private String originatingCmsCat;
    @Column(name = "DECEASED_IND")
    private String deceasedInd;
    @Column(name = "NFA_IND")
    private String nfaInd;
    @Column(name = "CUSTODY_IND")
    private String custodyInd;
    @Column(name = "PSR_AREA")
    private String psrArea;
    @Column(name = "OWNING_PRISON_AREA")
    private String owningPrisonArea;
    @Column(name = "OWNING_PROBATION_AREA")
    private String owningProbationArea;
    @Column(name = "PRIMARY_LOCATION_AREA")
    private String primaryLocationArea;
    @Column(name = "AWAITING_PROB_AREA")
    private String awaitingProbArea;
    @Column(name = "AWAITING_PRIS_AREA")
    private String awaitingPrisArea;
    @Column(name = "PHYSICAL_LOCATION_OTHER")
    private String physicalLocationOther;
    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;
    @Column(name = "MERGED_IND")
    private String mergedInd;
    @Column(name = "CMS_MESSAGE")
    private String cmsMessage;
    @Column(name = "ICMSCLIREF")
    private String icmscliref;
    @Column(name = "ICMSSUPREF")
    private String icmssupref;
    @Column(name = "ICMSLINKREF")
    private String icmslinkref;
    @Column(name = "CMS_EVENT_NUMBER")
    private Long cmsEventNumber;
    @Column(name = "POTENTIAL_DELETION_DATE")
    private LocalDateTime potentialDeletionDate;
    @Column(name = "RETAINED_IND")
    private String retainedInd;
    @Column(name = "RETAINED_REASON")
    private String retainedReason;
    @Column(name = "LIFE_IND")
    private String lifeInd;
    @Column(name = "RELEASE_POT_DELETION_DATE")
    private LocalDateTime releasePotDeletionDate;
    @Column(name = "RESTORE_PNC_IND")
    private String restorePncInd;
    @Column(name = "PHYSICAL_LOCATION")
    private String physicalLocation;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "DISCHARGE_CODE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "DISCHARGE_CODE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement dischargeCode;


    @Column(name = "HOST_CPA")
    private String hostCpa;
    @OneToMany
    @JoinColumn(name = "OFFENDER_PK", referencedColumnName = "OFFENDER_PK")
    private List<OasysAssessmentGroup> oasysAssessmentGroups;

    @OneToMany
    @JoinColumn(name = "OFFENDER_PK", referencedColumnName = "OFFENDER_PK")
    private List<OffenderAlias> offenderAliases;

}
