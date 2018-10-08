package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_CASES")
public class OffenderCase {
    @Id
    @Column(name = "CASE_ID")
    private Long caseId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "CASE_SEQ")
    private int caseSeq;
    @Column(name = "CASE_INFO_NUMBER")
    private String caseInfoNumber;
    @Column(name = "CASE_TYPE")
    private String caseType;
    @Column(name = "CASE_STATUS")
    private String caseStatus;
    @Column(name = "COMBINED_CASE_ID")
    private Long combinedCaseId;
    @Column(name = "BEGIN_DATE")
    private Timestamp beginDate;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "CASE_INFO_PREFIX")
    private String caseInfoPrefix;
    @Column(name = "VICTIM_LIAISON_UNIT")
    private String victimLiaisonUnit;
    @Column(name = "STATUS_UPDATE_REASON")
    private String statusUpdateReason;
    @Column(name = "STATUS_UPDATE_COMMENT")
    private String statusUpdateComment;
    @Column(name = "STATUS_UPDATE_DATE")
    private Timestamp statusUpdateDate;
    @Column(name = "STATUS_UPDATE_STAFF_ID")
    private Integer statusUpdateStaffId;
    @Column(name = "LIDS_CASE_NUMBER")
    private Integer lidsCaseNumber;
    @Column(name = "NOMLEGALCASEREF")
    private Integer nomLegalCaseRef;
    @Column(name = "NOMLEGALCASEREFTRANSTO")
    private Integer nomLegalCaserefTransTo;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;

    @Column(name = "AUDIT_TIMESTAMP")
    private Timestamp auditTimestamp;
    @Column(name = "AUDIT_USER_ID")
    private String auditUserId;
    @Column(name = "AUDIT_MODULE_NAME")
    private String auditModuleName;
    @Column(name = "AUDIT_CLIENT_USER_ID")
    private String auditClientUserId;
    @Column(name = "AUDIT_CLIENT_IP_ADDRESS")
    private String auditClientIpAddress;
    @Column(name = "AUDIT_CLIENT_WORKSTATION_NAME")
    private String auditClientWorkstationName;
    @Column(name = "AUDIT_ADDITIONAL_INFO")
    private String auditAdditionalInfo;

}
