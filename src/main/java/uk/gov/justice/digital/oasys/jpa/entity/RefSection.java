package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REF_SECTION")
@IdClass(RefSectionPK.class)
public class RefSection {
    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Id
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Id
    @Column(name = "REF_SECTION_CODE")
    private String refSectionCode;
    @Column(name = "REF_SECTION_UK")
    private Long refSectionUk;
    @Column(name = "FORM_SEQUENCE")
    private Long formSequence;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "SECTION_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "SECTION_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement sectionType;

    @Column(name = "CRIM_NEED_SCORE_THRESHOLD")
    private Long crimNeedScoreThreshold;
    @Column(name = "SCORED_FOR_OGP")
    private String scoredForOgp;
    @Column(name = "SCORED_FOR_OVP")
    private String scoredForOvp;
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

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "REF_ASS_VERSION_CODE", referencedColumnName = "REF_ASS_VERSION_CODE"),
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER"),
            @JoinColumn(name = "REF_SECTION_CODE", referencedColumnName = "REF_SECTION_CODE")
    })
    private List<RefQuestion> refQuestions;

    public boolean hasSectionType(){
        return sectionType != null;
    }

}
