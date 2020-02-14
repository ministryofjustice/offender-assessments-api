package uk.gov.justice.digital.oasys.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFENDER_ALIAS")
public class OffenderAlias {
    @Id
    @Column(name = "OFFENDER_ALIAS_PK")
    private Long offenderAliasPk;
    @Column(name = "DISPLAY_SORT")
    private Long displaySort;
    @Column(name = "ALIAS_DATE_OF_BIRTH")
    private LocalDate aliasDateOfBirth;
    @Column(name = "ALIAS_FAMILY_NAME")
    private String aliasFamilyName;
    @Column(name = "NAME_SOUNDSLIKE")
    private String nameSoundslike;
    @Column(name = "ALIAS_FORENAME_1")
    private String aliasForename1;
    @Column(name = "ALIAS_FORENAME_2")
    private String aliasForename2;
    @Column(name = "MIG_GUID")
    private String migGuid;
    @Column(name = "MIG_ID")
    private String migId;
    @Column(name = "CHECKSUM")
    private String checksum;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "LASTUPD_DATE")
    private Timestamp lastupdDate;
    @Column(name = "LASTUPD_USER")
    private String lastupdUser;

}
