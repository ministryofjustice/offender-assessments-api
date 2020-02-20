package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class TeamPK implements Serializable {
    @Column(name = "CT_AREA_EST_CODE")
    @Id
    private String ctAreaEstCode;
    @Column(name = "DIVISION_CODE")
    @Id
    private String divisionCode;
    @Column(name = "TEAM_CODE")
    @Id
    private String teamCode;

}
