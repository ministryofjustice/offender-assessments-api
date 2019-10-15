package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Time;

@Data
@EqualsAndHashCode(exclude = {"offenceBlock"})
@Entity
@Table(name = "OFFENCE_SENTENCE_DETAIL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffenceSentenceDetail {
    @Id
    @Column(name = "OFFENCE_SENTENCE_DETAIL_PK")
    private Long offenceSentenceDetailPk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "CJA_UNPAID_HOURS")
    private Long cjaUnpaidHours;
    @Column(name = "CJA_SUPERVISION_MONTHS")
    private Long cjaSupervisionMonths;
    @Column(name = "ACTIVITY_DESC")
    private String activityDesc;
    @Column(name = "YES_NO_ELM")
    private String yesNoElm;
    @Column(name = "YES_NO_CAT")
    private String yesNoCat;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "SENTENCE_ATTRIBUTE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "SENTENCE_ATTRIBUTE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement sentenceAttribute;

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

    @OneToOne
    @JoinColumn(name = "OFFENCE_BLOCK_PK", referencedColumnName = "OFFENCE_BLOCK_PK")
    private OffenceBlock offenceBlock;

}
