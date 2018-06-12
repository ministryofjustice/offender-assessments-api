package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Data
@Entity
@IdClass(TeamPK.class)
public class Team {
    @Column(name = "TEAM_UK")
    private Long teamUk;
    @Id
    @Column(name = "CT_AREA_EST_CODE")
    private String ctAreaEstCode;
    @Id
    @Column(name = "DIVISION_CODE")
    private String divisionCode;
    @Id
    @Column(name = "TEAM_CODE")
    private String teamCode;
    @Column(name = "TEAM_NAME")
    private String teamName;
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
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

}
