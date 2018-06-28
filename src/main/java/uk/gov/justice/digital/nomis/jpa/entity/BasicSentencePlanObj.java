package uk.gov.justice.digital.nomis.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "BASIC_SENTENCE_PLAN_OBJ", schema = "EOR", catalog = "")
public class BasicSentencePlanObj {
    private Long basicSentPlanObjPk;
    private Long displaySort;
    private String includeInPlanInd;
    private String offenceBehavLinkElm;
    private String offenceBehavLinkCat;
    private String objectiveText;
    private String measureText;
    private String whatWorkText;
    private String whoWillDoWorkText;
    private String timescalesText;
    private Long oasysSetPk;
    private Time dateOpened;
    private Time dateCompleted;
    private String problemAreaCompInd;
    private String migGuid;
    private String migId;
    private String checksum;
    private Time createDate;
    private String createUser;
    private Time lastupdDate;
    private String lastupdUser;
    private Long cfLastBcsInt;
    private Long cfOrigBcsInt;

    @Id
    @Column(name = "BASIC_SENT_PLAN_OBJ_PK")
    public Long getBasicSentPlanObjPk() {
        return basicSentPlanObjPk;
    }

    public void setBasicSentPlanObjPk(Long basicSentPlanObjPk) {
        this.basicSentPlanObjPk = basicSentPlanObjPk;
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
    @Column(name = "INCLUDE_IN_PLAN_IND")
    public String getIncludeInPlanInd() {
        return includeInPlanInd;
    }

    public void setIncludeInPlanInd(String includeInPlanInd) {
        this.includeInPlanInd = includeInPlanInd;
    }

    @Basic
    @Column(name = "OFFENCE_BEHAV_LINK_ELM")
    public String getOffenceBehavLinkElm() {
        return offenceBehavLinkElm;
    }

    public void setOffenceBehavLinkElm(String offenceBehavLinkElm) {
        this.offenceBehavLinkElm = offenceBehavLinkElm;
    }

    @Basic
    @Column(name = "OFFENCE_BEHAV_LINK_CAT")
    public String getOffenceBehavLinkCat() {
        return offenceBehavLinkCat;
    }

    public void setOffenceBehavLinkCat(String offenceBehavLinkCat) {
        this.offenceBehavLinkCat = offenceBehavLinkCat;
    }

    @Basic
    @Column(name = "OBJECTIVE_TEXT")
    public String getObjectiveText() {
        return objectiveText;
    }

    public void setObjectiveText(String objectiveText) {
        this.objectiveText = objectiveText;
    }

    @Basic
    @Column(name = "MEASURE_TEXT")
    public String getMeasureText() {
        return measureText;
    }

    public void setMeasureText(String measureText) {
        this.measureText = measureText;
    }

    @Basic
    @Column(name = "WHAT_WORK_TEXT")
    public String getWhatWorkText() {
        return whatWorkText;
    }

    public void setWhatWorkText(String whatWorkText) {
        this.whatWorkText = whatWorkText;
    }

    @Basic
    @Column(name = "WHO_WILL_DO_WORK_TEXT")
    public String getWhoWillDoWorkText() {
        return whoWillDoWorkText;
    }

    public void setWhoWillDoWorkText(String whoWillDoWorkText) {
        this.whoWillDoWorkText = whoWillDoWorkText;
    }

    @Basic
    @Column(name = "TIMESCALES_TEXT")
    public String getTimescalesText() {
        return timescalesText;
    }

    public void setTimescalesText(String timescalesText) {
        this.timescalesText = timescalesText;
    }

    @Basic
    @Column(name = "OASYS_SET_PK")
    public Long getOasysSetPk() {
        return oasysSetPk;
    }

    public void setOasysSetPk(Long oasysSetPk) {
        this.oasysSetPk = oasysSetPk;
    }

    @Basic
    @Column(name = "DATE_OPENED")
    public Time getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Time dateOpened) {
        this.dateOpened = dateOpened;
    }

    @Basic
    @Column(name = "DATE_COMPLETED")
    public Time getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Time dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Basic
    @Column(name = "PROBLEM_AREA_COMP_IND")
    public String getProblemAreaCompInd() {
        return problemAreaCompInd;
    }

    public void setProblemAreaCompInd(String problemAreaCompInd) {
        this.problemAreaCompInd = problemAreaCompInd;
    }

    @Basic
    @Column(name = "MIG_GUID")
    public String getMigGuid() {
        return migGuid;
    }

    public void setMigGuid(String migGuid) {
        this.migGuid = migGuid;
    }

    @Basic
    @Column(name = "MIG_ID")
    public String getMigId() {
        return migId;
    }

    public void setMigId(String migId) {
        this.migId = migId;
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
    @Column(name = "CF_LAST_BCS_INT")
    public Long getCfLastBcsInt() {
        return cfLastBcsInt;
    }

    public void setCfLastBcsInt(Long cfLastBcsInt) {
        this.cfLastBcsInt = cfLastBcsInt;
    }

    @Basic
    @Column(name = "CF_ORIG_BCS_INT")
    public Long getCfOrigBcsInt() {
        return cfOrigBcsInt;
    }

    public void setCfOrigBcsInt(Long cfOrigBcsInt) {
        this.cfOrigBcsInt = cfOrigBcsInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicSentencePlanObj that = (BasicSentencePlanObj) o;
        return Objects.equals(basicSentPlanObjPk, that.basicSentPlanObjPk) &&
                Objects.equals(displaySort, that.displaySort) &&
                Objects.equals(includeInPlanInd, that.includeInPlanInd) &&
                Objects.equals(offenceBehavLinkElm, that.offenceBehavLinkElm) &&
                Objects.equals(offenceBehavLinkCat, that.offenceBehavLinkCat) &&
                Objects.equals(objectiveText, that.objectiveText) &&
                Objects.equals(measureText, that.measureText) &&
                Objects.equals(whatWorkText, that.whatWorkText) &&
                Objects.equals(whoWillDoWorkText, that.whoWillDoWorkText) &&
                Objects.equals(timescalesText, that.timescalesText) &&
                Objects.equals(oasysSetPk, that.oasysSetPk) &&
                Objects.equals(dateOpened, that.dateOpened) &&
                Objects.equals(dateCompleted, that.dateCompleted) &&
                Objects.equals(problemAreaCompInd, that.problemAreaCompInd) &&
                Objects.equals(migGuid, that.migGuid) &&
                Objects.equals(migId, that.migId) &&
                Objects.equals(checksum, that.checksum) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(lastupdDate, that.lastupdDate) &&
                Objects.equals(lastupdUser, that.lastupdUser) &&
                Objects.equals(cfLastBcsInt, that.cfLastBcsInt) &&
                Objects.equals(cfOrigBcsInt, that.cfOrigBcsInt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(basicSentPlanObjPk, displaySort, includeInPlanInd, offenceBehavLinkElm, offenceBehavLinkCat, objectiveText, measureText, whatWorkText, whoWillDoWorkText, timescalesText, oasysSetPk, dateOpened, dateCompleted, problemAreaCompInd, migGuid, migId, checksum, createDate, createUser, lastupdDate, lastupdUser, cfLastBcsInt, cfOrigBcsInt);
    }
}
