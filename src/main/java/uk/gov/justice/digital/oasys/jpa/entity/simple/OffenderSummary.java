package uk.gov.justice.digital.oasys.jpa.entity.simple;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "OFFENDER")
public class OffenderSummary {

    @Id
    @Column(name = "OFFENDER_PK")
    private Long offenderPk;
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
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "FORENAME_1")
    private String forename1;
    @Column(name = "FORENAME_2")
    private String forename2;
    @Column(name = "FORENAME_3")
    private String forename3;
    //these risks come from either Nomis or Delius
    @Column(name = "RISK_TO_OTHERS_ELM")
    private String riskToSelf;
    @Column(name = "RISK_TO_SELF_ELM")
    private String riskToOthers;
}