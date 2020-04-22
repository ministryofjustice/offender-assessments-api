package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OASYS_BCS_PART")
public class OasysBcsPart {
    @Id
    @Column(name = "OASYS_BCS_PART_PK")
    private Long oasysBcsPartPk;
    @Column(name = "BCS_PART_CAT")
    private String bcsPartCat;
    @Column(name = "BCS_PART_ELM")
    private String bcsPart;
    @Column(name = "OFFENDER_PK")
    private Long offenderPk;
    @Column(name = "BCS_PART_STATUS_CAT")
    private String bcsPartStatusCat;
    @Column(name = "BCS_PART_STATUS_ELM")
    private String bcsPartStatus;
    @Column(name = "PART1_CHECKED_IND")
    private String part1CheckedInd;
    @Column(name = "PART1_CHECKED_DATE")
    private LocalDateTime part1CheckedDate;
    @Column(name = "BCS_PART_USER_AREA")
    private String bcsPartUserArea;
    @Column(name = "BCS_PART_USER_POSITION")
    private String bcsPartUserPosition;
    @Column(name = "BCS_PART_COMP_DATE")
    private LocalDateTime bcsPartCompDate;
    @Column(name = "OASYS_SET_PK")
    private Long oasysSetPk;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastupdDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;
    @Column(name = "PRA_COMPLETE")
    private String praComplete;
    @Column(name = "PRA_COMP_USER")
    private String praCompUser;
    @Column(name = "PRA_COMP_DATE")
    private LocalDateTime praCompDate;
    @Column(name = "LOCK_INCOMPLETE_REASON_CAT")
    private String lockIncompleteReasonCat;
    @Column(name = "LOCK_INCOMPLETE_REASON_ELM")
    private String lockIncompleteReason;
    @Column(name = "LOCK_INCOMPLETE_OTHER_TEXT")
    private String lockIncompleteOtherText;

    @OneToOne
    @JoinColumn(name = "PART1_CHECKED_USER", referencedColumnName = "OASYS_USER_CODE")
    private OasysUser part1CheckedUser;
    @OneToOne
    @JoinColumn(name = "BCS_PART_USER", referencedColumnName = "OASYS_USER_CODE")
    private OasysUser bcsPartUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OasysBcsPart))
            return false;

        OasysBcsPart other = (OasysBcsPart) o;
        return getOasysBcsPartPk() != null &&
                getOasysBcsPartPk().equals(other.getOasysBcsPartPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
