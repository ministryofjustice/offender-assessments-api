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
import java.time.LocalDate;

@Data
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

}
