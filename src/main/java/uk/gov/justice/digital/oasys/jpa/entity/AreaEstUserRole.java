package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "AREA_EST_USER_ROLE")
@IdClass(AreaEstUserRolePK.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaEstUserRole {
    @Column(name = "AREA_EST_USER_ROLE_UK")
    private Long areaEstUserRoleUk;
    @Id
    @Column(name = "OASYS_USER_CODE")
    private String oasysUserCode;
    @Id
    @Column(name = "REF_ROLE_CODE")
    private String refRoleCode;
    @Id
    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Column(name = "START_DATE")
    private Time startDate;
    @Column(name = "END_DATE")
    private Time endDate;
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

    @ManyToOne
    @JoinColumn(name = "REF_ROLE_CODE", insertable = false, updatable = false)
    private RefRole refRole;

}
