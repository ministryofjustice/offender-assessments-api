package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OASYS_SECTION")
public class OasysSection {
    @Id
    @Column(name = "OASYS_SECTION_PK")
    private Long oasysSectionPk;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
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
    private Set<OasysQuestion> oasysQuestions;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER"),
            @JoinColumn(name = "REF_SECTION_CODE", referencedColumnName = "REF_SECTION_CODE")})
    private RefSection refSection;

    public boolean hasRefSection(){
        return refSection != null;
    }

    public Map<String,String> getRefAnswers(String... questionKeys) {
        var keys = Set.of(questionKeys);
        return oasysQuestions.stream()
                .filter(q -> q.hasRefQuestion() && keys.contains(q.getRefQuestion().getRefQuestionCode()))
                .filter(OasysQuestion::hasOasysAnswer)
                .map(OasysQuestion::getOasysAnswer)
                .filter(OasysAnswer::hasRefAnswer)
                .map(OasysAnswer::getRefAnswer)
                .collect(Collectors.toMap(RefAnswer::getRefQuestionCode, RefAnswer::getRefAnswerCode));
    }
}
