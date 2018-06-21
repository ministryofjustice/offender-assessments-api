package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "OASYS_SECTION")
public class OasysSection {
    @Id
    @Column(name = "OASYS_SECTION_PK")
    private Long oasysSectionPk;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Column(name = "REF_SECTION_CODE")
    private String refSectionCode;
    @Column(name = "SECTION_STATUS_ELM")
    private String sectionStatusElm;
    @Column(name = "SECTION_STATUS_CAT")
    private String sectionStatusCat;
    @Column(name = "MISSING_NOTIONAL_ELM")
    private String missingNotionalElm;
    @Column(name = "MISSING_NOTIONAL_CAT")
    private String missingNotionalCat;
    @Column(name = "SECT_OGP_WEIGHTED_SCORE")
    private Long sectOgpWeightedScore;
    @Column(name = "SECT_OVP_WEIGHTED_SCORE")
    private Long sectOvpWeightedScore;
    @Column(name = "SECT_OTHER_WEIGHTED_SCORE")
    private Long sectOtherWeightedScore;
    @Column(name = "SECT_OVP_RAW_SCORE")
    private Long sectOvpRawScore;
    @Column(name = "SECT_OGP_RAW_SCORE")
    private Long sectOgpRawScore;
    @Column(name = "SECT_OTHER_RAW_SCORE")
    private Long sectOtherRawScore;
    @Column(name = "LOW_SCORE_NEED_ATTN_IND")
    private String lowScoreNeedAttnInd;
    @Column(name = "QA_REVIEW_PK")
    private Long qaReviewPk;
    @Column(name = "CURRENTLY_HIDDEN_IND")
    private String currentlyHiddenInd;
    @Column(name = "SECTION_PAGE_VISIBILITY")
    private Long sectionPageVisibility;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Time createDate;
    @Column(name = "LASTUPD_DATE")
    private Time lastupdDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;
    @Column(name = "OASYS_BCS_PART_PK")
    private Long oasysBcsPartPk;

    @OneToMany
    @JoinColumn(name = "OASYS_SECTION_PK")
    private List<OasysQuestion> oasysQuestions;

}
