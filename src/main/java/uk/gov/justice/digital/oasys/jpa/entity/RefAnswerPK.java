package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class RefAnswerPK implements Serializable {
    @Column(name = "REF_ASS_VERSION_CODE")
    @Id
    private String refAssVersionCode;
    @Column(name = "VERSION_NUMBER")
    @Id
    private String versionNumber;
    @Column(name = "REF_SECTION_CODE")
    @Id
    private String refSectionCode;
    @Column(name = "REF_QUESTION_CODE")
    @Id
    private String refQuestionCode;
    @Column(name = "REF_ANSWER_CODE")
    @Id
    private String refAnswerCode;

}
