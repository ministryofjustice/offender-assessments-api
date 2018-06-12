package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "CT_AREA_EST", schema = "EOR", catalog = "")
public class CtAreaEst {
    private Long ctAreaEstUk;
    private String ctAreaEstCode;
    private String areaEstCtid;
    private String areaEstName;
    private Time startDate;
    private Time endDate;
    private String laoContactNumber;
    private String showSdrObjInd;
    private String showSdrHintsInd;
    private String showCrimGraphInd;
    private String sdrConcernNoneInd;
    private String sdrConcernSuicideInd;
    private String sdrConcernSelfharmInd;
    private String sdrConcernCustodyInd;
    private String sdrConcernHostelInd;
    private String sdrConcernVulnerableInd;
    private String dataExportPath;
    private String nomisCode;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;
    private String psrLogoFilename;
    private String runQaSampleInd;
    private String bcsOnReceptionInd;

    @Basic
    @Column(name = "CT_AREA_EST_UK")
    public Long getCtAreaEstUk() {
        return ctAreaEstUk;
    }

    public void setCtAreaEstUk(Long ctAreaEstUk) {
        this.ctAreaEstUk = ctAreaEstUk;
    }

    @Id
    @Column(name = "CT_AREA_EST_CODE")
    public String getCtAreaEstCode() {
        return ctAreaEstCode;
    }

    public void setCtAreaEstCode(String ctAreaEstCode) {
        this.ctAreaEstCode = ctAreaEstCode;
    }

    @Basic
    @Column(name = "AREA_EST_CTID")
    public String getAreaEstCtid() {
        return areaEstCtid;
    }

    public void setAreaEstCtid(String areaEstCtid) {
        this.areaEstCtid = areaEstCtid;
    }

    @Basic
    @Column(name = "AREA_EST_NAME")
    public String getAreaEstName() {
        return areaEstName;
    }

    public void setAreaEstName(String areaEstName) {
        this.areaEstName = areaEstName;
    }

    @Basic
    @Column(name = "START_DATE")
    public Time getStartDate() {
        return startDate;
    }

    public void setStartDate(Time startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Time getEndDate() {
        return endDate;
    }

    public void setEndDate(Time endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "LAO_CONTACT_NUMBER")
    public String getLaoContactNumber() {
        return laoContactNumber;
    }

    public void setLaoContactNumber(String laoContactNumber) {
        this.laoContactNumber = laoContactNumber;
    }

    @Basic
    @Column(name = "SHOW_SDR_OBJ_IND")
    public String getShowSdrObjInd() {
        return showSdrObjInd;
    }

    public void setShowSdrObjInd(String showSdrObjInd) {
        this.showSdrObjInd = showSdrObjInd;
    }

    @Basic
    @Column(name = "SHOW_SDR_HINTS_IND")
    public String getShowSdrHintsInd() {
        return showSdrHintsInd;
    }

    public void setShowSdrHintsInd(String showSdrHintsInd) {
        this.showSdrHintsInd = showSdrHintsInd;
    }

    @Basic
    @Column(name = "SHOW_CRIM_GRAPH_IND")
    public String getShowCrimGraphInd() {
        return showCrimGraphInd;
    }

    public void setShowCrimGraphInd(String showCrimGraphInd) {
        this.showCrimGraphInd = showCrimGraphInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_NONE_IND")
    public String getSdrConcernNoneInd() {
        return sdrConcernNoneInd;
    }

    public void setSdrConcernNoneInd(String sdrConcernNoneInd) {
        this.sdrConcernNoneInd = sdrConcernNoneInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_SUICIDE_IND")
    public String getSdrConcernSuicideInd() {
        return sdrConcernSuicideInd;
    }

    public void setSdrConcernSuicideInd(String sdrConcernSuicideInd) {
        this.sdrConcernSuicideInd = sdrConcernSuicideInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_SELFHARM_IND")
    public String getSdrConcernSelfharmInd() {
        return sdrConcernSelfharmInd;
    }

    public void setSdrConcernSelfharmInd(String sdrConcernSelfharmInd) {
        this.sdrConcernSelfharmInd = sdrConcernSelfharmInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_CUSTODY_IND")
    public String getSdrConcernCustodyInd() {
        return sdrConcernCustodyInd;
    }

    public void setSdrConcernCustodyInd(String sdrConcernCustodyInd) {
        this.sdrConcernCustodyInd = sdrConcernCustodyInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_HOSTEL_IND")
    public String getSdrConcernHostelInd() {
        return sdrConcernHostelInd;
    }

    public void setSdrConcernHostelInd(String sdrConcernHostelInd) {
        this.sdrConcernHostelInd = sdrConcernHostelInd;
    }

    @Basic
    @Column(name = "SDR_CONCERN_VULNERABLE_IND")
    public String getSdrConcernVulnerableInd() {
        return sdrConcernVulnerableInd;
    }

    public void setSdrConcernVulnerableInd(String sdrConcernVulnerableInd) {
        this.sdrConcernVulnerableInd = sdrConcernVulnerableInd;
    }

    @Basic
    @Column(name = "DATA_EXPORT_PATH")
    public String getDataExportPath() {
        return dataExportPath;
    }

    public void setDataExportPath(String dataExportPath) {
        this.dataExportPath = dataExportPath;
    }

    @Basic
    @Column(name = "NOMIS_CODE")
    public String getNomisCode() {
        return nomisCode;
    }

    public void setNomisCode(String nomisCode) {
        this.nomisCode = nomisCode;
    }

    @Basic
    @Column(name = "CHECKSUM")
    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Time getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Time createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_USER")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "LASTUPD_DATE")
    public Time getLastupdDate() {
        return lastupdDate;
    }

    public void setLastupdDate(Time lastupdDate) {
        this.lastupdDate = lastupdDate;
    }

    @Basic
    @Column(name = "LASTUPD_USER")
    public String getLastupdUser() {
        return lastupdUser;
    }

    public void setLastupdUser(String lastupdUser) {
        this.lastupdUser = lastupdUser;
    }

    @Basic
    @Column(name = "PSR_LOGO_FILENAME")
    public String getPsrLogoFilename() {
        return psrLogoFilename;
    }

    public void setPsrLogoFilename(String psrLogoFilename) {
        this.psrLogoFilename = psrLogoFilename;
    }

    @Basic
    @Column(name = "RUN_QA_SAMPLE_IND")
    public String getRunQaSampleInd() {
        return runQaSampleInd;
    }

    public void setRunQaSampleInd(String runQaSampleInd) {
        this.runQaSampleInd = runQaSampleInd;
    }

    @Basic
    @Column(name = "BCS_ON_RECEPTION_IND")
    public String getBcsOnReceptionInd() {
        return bcsOnReceptionInd;
    }

    public void setBcsOnReceptionInd(String bcsOnReceptionInd) {
        this.bcsOnReceptionInd = bcsOnReceptionInd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CtAreaEst ctAreaEst = (CtAreaEst) o;
        return Objects.equals(ctAreaEstUk, ctAreaEst.ctAreaEstUk) &&
                Objects.equals(ctAreaEstCode, ctAreaEst.ctAreaEstCode) &&
                Objects.equals(areaEstCtid, ctAreaEst.areaEstCtid) &&
                Objects.equals(areaEstName, ctAreaEst.areaEstName) &&
                Objects.equals(startDate, ctAreaEst.startDate) &&
                Objects.equals(endDate, ctAreaEst.endDate) &&
                Objects.equals(laoContactNumber, ctAreaEst.laoContactNumber) &&
                Objects.equals(showSdrObjInd, ctAreaEst.showSdrObjInd) &&
                Objects.equals(showSdrHintsInd, ctAreaEst.showSdrHintsInd) &&
                Objects.equals(showCrimGraphInd, ctAreaEst.showCrimGraphInd) &&
                Objects.equals(sdrConcernNoneInd, ctAreaEst.sdrConcernNoneInd) &&
                Objects.equals(sdrConcernSuicideInd, ctAreaEst.sdrConcernSuicideInd) &&
                Objects.equals(sdrConcernSelfharmInd, ctAreaEst.sdrConcernSelfharmInd) &&
                Objects.equals(sdrConcernCustodyInd, ctAreaEst.sdrConcernCustodyInd) &&
                Objects.equals(sdrConcernHostelInd, ctAreaEst.sdrConcernHostelInd) &&
                Objects.equals(sdrConcernVulnerableInd, ctAreaEst.sdrConcernVulnerableInd) &&
                Objects.equals(dataExportPath, ctAreaEst.dataExportPath) &&
                Objects.equals(nomisCode, ctAreaEst.nomisCode) &&
                Objects.equals(checksum, ctAreaEst.checksum) &&
                Objects.equals(createDate, ctAreaEst.createDate) &&
                Objects.equals(createUser, ctAreaEst.createUser) &&
                Objects.equals(lastupdDate, ctAreaEst.lastupdDate) &&
                Objects.equals(lastupdUser, ctAreaEst.lastupdUser) &&
                Objects.equals(psrLogoFilename, ctAreaEst.psrLogoFilename) &&
                Objects.equals(runQaSampleInd, ctAreaEst.runQaSampleInd) &&
                Objects.equals(bcsOnReceptionInd, ctAreaEst.bcsOnReceptionInd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ctAreaEstUk, ctAreaEstCode, areaEstCtid, areaEstName, startDate, endDate, laoContactNumber, showSdrObjInd, showSdrHintsInd, showCrimGraphInd, sdrConcernNoneInd, sdrConcernSuicideInd, sdrConcernSelfharmInd, sdrConcernCustodyInd, sdrConcernHostelInd, sdrConcernVulnerableInd, dataExportPath, nomisCode, checksum, createDate, createUser, lastupdDate, lastupdUser, psrLogoFilename, runQaSampleInd, bcsOnReceptionInd);
    }
}
