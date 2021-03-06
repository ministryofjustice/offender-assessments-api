package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "REF_ASS_VERSION")
@IdClass(RefAssessmentVersionPK.class)
public class RefAssessmentVersion {
    @Id
    @Column(name = "REF_ASS_VERSION_CODE")
    private String refAssVersionCode;
    @Id
    @Column(name = "VERSION_NUMBER")
    private String versionNumber;
    @Id
    @Column(name = "REF_ASS_VERSION_UK")
    private Long refAssVersionUk;
    @Column(name = "START_DATE")
    private Time startDate;
    @Column(name = "END_DATE")
    private Time endDate;
    @Column(name = "OASYS_FORM_VERSION")
    private Long oasysFormVersion;
    @Column(name = "OASYS_SCORING_ALG_VERSION")
    private Long oasysScoringAlgVersion;
    @Column(name = "REF_MODULE_CODE")
    private String refModuleCode;
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
            @JoinColumn(name = "VERSION_NUMBER", referencedColumnName = "VERSION_NUMBER")
    })
    private List<RefSection> refSections;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefAssessmentVersion)) return false;
        RefAssessmentVersion that = (RefAssessmentVersion) o;
        return Objects.nonNull(getRefAssVersionCode()) &&
                Objects.nonNull(getVersionNumber()) &&
                Objects.equals(getRefAssVersionCode(), that.getRefAssVersionCode()) &&
                Objects.equals(getVersionNumber(), that.getVersionNumber());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
