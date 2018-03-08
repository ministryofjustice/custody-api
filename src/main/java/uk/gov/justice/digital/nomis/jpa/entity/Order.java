package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    private Long orderId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "CASE_ID")
    private Long caseId;
    @Column(name = "COURT_DATE")
    private Timestamp courtDate;
    @Column(name = "ORDER_TYPE")
    private String orderType;
    @Column(name = "ISSUING_AGY_LOC_ID")
    private String issuingAgyLocId;
    @Column(name = "COURT_INFO_ID")
    private String courtInfoId;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "DUE_DATE")
    private Timestamp dueDate;
    @Column(name = "COURT_SERIOUSNESS_LEVEL")
    private String courtSeriousnessLevel;
    @Column(name = "ORDER_SERIOUSNESS_LEVEL")
    private String orderSeriousnessLevel;
    @Column(name = "REQUEST_DATE")
    private Timestamp requestDate;
    @Column(name = "STAFF_WORK_ID")
    private Integer staffWorkId;
    @Column(name = "EVENT_ID")
    private Integer eventId;
    @Column(name = "COMPLETE_DATE")
    private Timestamp completeDate;
    @Column(name = "COMPLETE_STAFF_ID")
    private Integer completeStaffId;
    @Column(name = "INTERVENTION_TIER_CODE")
    private String interventionTierCode;
    @Column(name = "NON_REPORT_FLAG")
    private String nonReportFlag;
    @Column(name = "CPS_RECEIVED_DATE")
    private Timestamp cpsReceivedDate;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "ISSUE_DATE")
    private Timestamp issueDate;
    @Column(name = "MESSAGE_ID")
    private String messageId;
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
    @Column(name = "WORKFLOW_ID")
    private Long workflowId;
    @Column(name = "OFFENDER_PROCEEDING_ID")
    private Integer offenderProceedingId;

}
