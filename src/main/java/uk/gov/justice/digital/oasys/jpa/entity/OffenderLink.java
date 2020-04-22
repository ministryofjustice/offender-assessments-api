package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "OFFENDER_LINK")
public class OffenderLink {

    @Id
    @Column(name = "OFFENDER_LINK_PK")
    private Long offenderLinkPk;

    @Column(name = "INITIATING_OFFENDER")
    private Long initiatingOffenderPK;

    @Column(name = "DECIDING_OFFENDER")
    private Long decidingOffenderPK;

    @Column(name = "MERGED_OFFENDER")
    private Long mergedOffenderPK;

    @Column(name = "REVERSED_IND")
    private Boolean reversed;

    @Column(name = "REVERSAL_ALLOWED_IND")
    private Boolean reversalAllowed;

    @Column(name = "ORIG_INITATING_PNC")
    private String originalInitiatingPnc;

    @Column(name = "DECIDER_CANCEL_IND")
    private Boolean deciderCancel;

    @Column(name = "LASTUPD_DATE")
    private LocalDateTime lastUpdateDate;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "LINK_TYPE_CAT", referencedColumnName = "REF_CATEGORY_CODE"),
            @JoinColumn(name = "LINK_TYPE_ELM", referencedColumnName = "REF_ELEMENT_CODE")
    })
    private RefElement linkType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OffenderLink))
            return false;

        OffenderLink other = (OffenderLink) o;
        return getOffenderLinkPk() != null &&
                getOffenderLinkPk().equals(other.getOffenderLinkPk());
    }

    @Override
    public int hashCode() {
        return 31;
    }


}
