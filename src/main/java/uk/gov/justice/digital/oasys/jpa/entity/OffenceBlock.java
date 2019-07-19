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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "OFFENCE_BLOCK")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffenceBlock {
    @Id
    @Column(name = "OFFENCE_BLOCK_PK")
    private Long offenceBlockPk;
    @Column(name = "ADD_OFF_FTXT_LGCYDAT")
    private String addOffFtxtLgcydat;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "OFFENCE_DATE")
    private Time offenceDate;
    @Column(name = "RESENTENCE_FOR_BREACH_IND")
    private String resentenceForBreachInd;
    @Column(name = "SENTENCE_DATE")
    private Time sentenceDate;
    @Column(name = "SENTENCE_LENGTH_CPO_HOURS")
    private Long sentenceLengthCpoHours;
    @Column(name = "SENTENCE_LENGTH_CRO_M")
    private Long sentenceLengthCroM;
    @Column(name = "SENTENCE_OTHER_FREE_TEXT")
    private String sentenceOtherFreeText;
    @Column(name = "SENT_LENGTH_CUST_DAYS")
    private Long sentLengthCustDays;
    @Column(name = "SNTNC_EXTND_LNGTH_MNTHS")
    private Long sntncExtndLngthMnths;
    @Column(name = "SENTENCE_COURT_RECOM")
    private String sentenceCourtRecom;
    @Column(name = "DISQUALIFICATION_ORDER")
    private String disqualificationOrder;
    @Column(name = "SNT_ALIC_CNDS2_LGCYDAT")
    private String sntAlicCnds2Lgcydat;
    @Column(name = "ORDER_AMENDED_IND")
    private String orderAmendedInd;
    @Column(name = "ORDER_AMENDED_DATE")
    private Time orderAmendedDate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OFFENCE_BLOCK_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "OFFENCE_BLOCK_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement offenceBlockType;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "LEVEL_OF_SERIOUSNESS_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "LEVEL_OF_SERIOUSNESS_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement levelOfSeriousness;

    @Column(name = "COURT_PK")
    private Long courtPk;
    @Column(name = "COURT_OTHER_TEXT")
    private String courtOtherText;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
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
    @Column(name = "BCS_SENTENCE_TYPE_ELM")
    private String bcsSentenceTypeElm;
    @Column(name = "BCS_SENTENCE_TYPE_CAT")
    private String bcsSentenceTypeCat;

    @OneToOne(mappedBy = "offenceBlock", optional = false)
    private OffenceSentenceDetail offenceSentenceDetail;

    @OneToOne
    @JoinColumn(name = "SENTENCE_CODE", referencedColumnName = "SENTENCE_CODE")
    private Sentence sentence;

    @OneToOne
    @JoinColumn(name = "OASYS_SET_PK", referencedColumnName = "OASYS_SET_PK")
    private OasysSet oasysSet;
}
