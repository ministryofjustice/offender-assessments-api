package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "OASYS_ASSESSMENT_GROUP")
@Builder
public class AssessmentGroup {

    @Id
    @Column(name = "OASYS_ASSESSMENT_GROUP_PK")
    private Long oasysAssessmentGroupPk;
    @Column(name = "OFFENDER_PK")
    private Long offenderPk;
    @Column(name = "ASSESSMENT_DATE_CLOSED")
    private LocalDateTime assessmentDateClosed;
    @Column(name = "HISTORIC_STATUS_ELM")
    private String historicStatus;

    @OneToMany
    @JoinColumn(name = "OASYS_ASSESSMENT_GROUP_PK", referencedColumnName = "OASYS_ASSESSMENT_GROUP_PK")
    private Set<Assessment> assessments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssessmentGroup))
            return false;

        AssessmentGroup other = (AssessmentGroup) o;
        return oasysAssessmentGroupPk != null &&
                oasysAssessmentGroupPk.equals(other.getOasysAssessmentGroupPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}