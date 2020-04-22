package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "CMS_STUB_OFFENDER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmsStubOffender {
    @Id
    @Column(name = "CMS_STUB_OFFENDER_PK")
    private Long cmsStubOffenderPk;
    @Column(name = "CMS_PROB_NUMBER")
    private String cmsProbNumber;
    @Column(name = "PRISON_NUMBER")
    private String prisonNumber;
    @Column(name = "CRAMS_REF")
    private String cramsRef;
    @Column(name = "CMS_PRIS_NUMBER")
    private String cmsPrisNumber;
    @Column(name = "PNC")
    private String pnc;
    @Column(name = "CRO_NUMBER")
    private String croNumber;
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "FORENAME_1")
    private String forename1;
    @Column(name = "FORENAME_2")
    private String forename2;
    @Column(name = "FORENAME_3")
    private String forename3;
    @Column(name = "GENDER_ELM")
    private String genderElm;
    @Column(name = "GENDER_CAT")
    private String genderCat;
    @Column(name = "ETHNIC_CATEGORY_ELM")
    private String ethnicCategoryElm;
    @Column(name = "ETHNIC_CATEGORY_CAT")
    private String ethnicCategoryCat;
    @Column(name = "DATE_OF_BIRTH")
    private Time dateOfBirth;
    @Column(name = "LAO_INDICATOR")
    private String laoIndicator;
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
    @Column(name = "NOTES_ID")
    private String notesId;
    @Column(name = "LANGUAGE_ELM")
    private String languageElm;
    @Column(name = "LANGUAGE_CAT")
    private String languageCat;
    @Column(name = "RELIGION_ELM")
    private String religionElm;
    @Column(name = "RELIGION_CAT")
    private String religionCat;
    @Column(name = "CELL_LOCATION")
    private String cellLocation;
    @Column(name = "SECURITY_CATEGORY")
    private String securityCategory;
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
    @Column(name = "DISCHARGE_POSTCODE")
    private String dischargePostcode;
    @Column(name = "DISCHARGE_TELEPHONE_NUMBER")
    private String dischargeTelephoneNumber;
    @Column(name = "APPEALANT_IND")
    private String appealantInd;
    @Column(name = "CURFEW_DATE")
    private Time curfewDate;
    @Column(name = "PAROLE_ELIGIBILITY_DATE")
    private Time paroleEligibilityDate;
    @Column(name = "LICENCE_EXPIRY_DATE")
    private Time licenceExpiryDate;
    @Column(name = "NON_PAROLE_DATE")
    private Time nonParoleDate;
    @Column(name = "AUTOMATIC_RELEASE_DATE")
    private Time automaticReleaseDate;
    @Column(name = "CONDITIONAL_RELEASE_DATE")
    private Time conditionalReleaseDate;
    @Column(name = "SENTENCE_EXPIRY_DATE")
    private Time sentenceExpiryDate;
    @Column(name = "RISK_OF_SELF_HARM")
    private String riskOfSelfHarm;
    @Column(name = "FIRST_CONVICTION_AGE")
    private String firstConvictionAge;
    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Column(name = "RECEPTION_CODE_ELM")
    private String receptionCodeElm;
    @Column(name = "RECEPTION_CODE_CAT")
    private String receptionCodeCat;
    @Column(name = "DISCHARGE_CODE_ELM")
    private String dischargeCodeElm;
    @Column(name = "DISCHARGE_CODE_CAT")
    private String dischargeCodeCat;
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

        if (!(o instanceof CmsStubOffender))
            return false;

        CmsStubOffender other = (CmsStubOffender) o;
        return getCmsStubOffenderPk() != null &&
                getCmsStubOffenderPk().equals(other.getCmsStubOffenderPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
