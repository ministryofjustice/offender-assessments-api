package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "SENTENCE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sentence {
    @Column(name = "SENTENCE_UK")
    private Long sentenceUk;
    @Column(name = "SENTENCE_CTID")
    private String sentenceCtid;
    @Id
    @Column(name = "SENTENCE_CODE")
    private String sentenceCode;
    @Column(name = "SENTENCE_DESC")
    private String sentenceDesc;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "CUSTODIAL_IND")
    private String custodialInd;
    @Column(name = "CJA_IND")
    private String cjaInd;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ORDER_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "ORDER_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement orderType;

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

        if (!(o instanceof Sentence))
            return false;

        Sentence other = (Sentence) o;
        return getSentenceCode() != null &&
                getSentenceCode().equals(other.getSentenceCode());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
