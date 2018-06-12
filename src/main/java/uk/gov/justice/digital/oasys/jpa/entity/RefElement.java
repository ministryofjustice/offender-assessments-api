package uk.gov.justice.digital.oasys.jpa.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "REF_ELEMENT", schema = "EOR", catalog = "")
@IdClass(RefElementPK.class)
public class RefElement {
    private Long refElementUk;
    private String refCategoryCode;
    private String refElementCode;
    private String refElementCtid;
    private String refElementShortDesc;
    private String refElementDesc;
    private Long displaySort;
    private Time startDate;
    private Time endDate;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;

    @Basic
    @Column(name = "REF_ELEMENT_UK")
    public Long getRefElementUk() {
        return refElementUk;
    }

    public void setRefElementUk(Long refElementUk) {
        this.refElementUk = refElementUk;
    }

    @Id
    @Column(name = "REF_CATEGORY_CODE")
    public String getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(String refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    @Id
    @Column(name = "REF_ELEMENT_CODE")
    public String getRefElementCode() {
        return refElementCode;
    }

    public void setRefElementCode(String refElementCode) {
        this.refElementCode = refElementCode;
    }

    @Basic
    @Column(name = "REF_ELEMENT_CTID")
    public String getRefElementCtid() {
        return refElementCtid;
    }

    public void setRefElementCtid(String refElementCtid) {
        this.refElementCtid = refElementCtid;
    }

    @Basic
    @Column(name = "REF_ELEMENT_SHORT_DESC")
    public String getRefElementShortDesc() {
        return refElementShortDesc;
    }

    public void setRefElementShortDesc(String refElementShortDesc) {
        this.refElementShortDesc = refElementShortDesc;
    }

    @Basic
    @Column(name = "REF_ELEMENT_DESC")
    public String getRefElementDesc() {
        return refElementDesc;
    }

    public void setRefElementDesc(String refElementDesc) {
        this.refElementDesc = refElementDesc;
    }

    @Basic
    @Column(name = "DISPLAY_SORT")
    public Long getDisplaySort() {
        return displaySort;
    }

    public void setDisplaySort(Long displaySort) {
        this.displaySort = displaySort;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefElement that = (RefElement) o;
        return Objects.equals(refElementUk, that.refElementUk) &&
                Objects.equals(refCategoryCode, that.refCategoryCode) &&
                Objects.equals(refElementCode, that.refElementCode) &&
                Objects.equals(refElementCtid, that.refElementCtid) &&
                Objects.equals(refElementShortDesc, that.refElementShortDesc) &&
                Objects.equals(refElementDesc, that.refElementDesc) &&
                Objects.equals(displaySort, that.displaySort) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(checksum, that.checksum) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(lastupdDate, that.lastupdDate) &&
                Objects.equals(lastupdUser, that.lastupdUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(refElementUk, refCategoryCode, refElementCode, refElementCtid, refElementShortDesc, refElementDesc, displaySort, startDate, endDate, checksum, createDate, createUser, lastupdDate, lastupdUser);
    }
}
