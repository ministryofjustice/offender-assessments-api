package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class RefAssessmentVersionPK implements Serializable {
    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    @Id
    private String versionNumber;

}
