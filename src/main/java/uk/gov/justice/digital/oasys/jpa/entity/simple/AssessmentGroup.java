package uk.gov.justice.digital.oasys.jpa.entity.simple;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "OASYS_ASSESSMENT_GROUP")
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
}