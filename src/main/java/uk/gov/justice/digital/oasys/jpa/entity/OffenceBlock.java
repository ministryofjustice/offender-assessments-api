package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "OFFENCE_BLOCK")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OffenceBlock {
    @Id
    @Column(name = "OFFENCE_BLOCK_PK")
    private Long offenceBlockPk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "OFFENCE_DATE")
    private LocalDate offenceDate;
    @Column(name = "RESENTENCE_FOR_BREACH_IND")
    private String resentenceForBreachInd;
    @Column(name = "SENTENCE_DATE")
    private LocalDate sentenceDate;
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
    @Column(name = "ORDER_AMENDED_IND")
    private String orderAmendedInd;
    @Column(name = "ORDER_AMENDED_DATE")
    private LocalDateTime orderAmendedDate;

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

    @OneToOne
    @JoinColumn(name = "COURT_PK", referencedColumnName = "COURT_PK")
    private Court court;
    @Column(name = "COURT_OTHER_TEXT")
    private String courtOtherText;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToOne(mappedBy = "offenceBlock")
    private OffenceSentenceDetail offenceSentenceDetail;

    @OneToOne
    @JoinColumn(name = "SENTENCE_CODE", referencedColumnName = "SENTENCE_CODE")
    private Sentence sentence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OffenceBlock))
            return false;

        OffenceBlock other = (OffenceBlock) o;
        return getOffenceBlockPk() != null &&
                getOffenceBlockPk().equals(other.getOffenceBlockPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
