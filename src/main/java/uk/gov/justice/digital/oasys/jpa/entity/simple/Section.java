package uk.gov.justice.digital.oasys.jpa.entity.simple;

import lombok.*;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.OasysQuestion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAnswer;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "OASYS_SECTION")
@Builder
public class Section {

    @Id
    @Column(name = "OASYS_SECTION_PK")
    private Long oasysSectionPk;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "SECTION_STATUS_ELM")
    private String sectionStatusElm;
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
