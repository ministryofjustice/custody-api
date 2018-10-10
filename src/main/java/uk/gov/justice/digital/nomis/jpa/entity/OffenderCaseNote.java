package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_CASE_NOTES")
public class OffenderCaseNote {
    @Id
    @Column(name = "CASE_NOTE_ID")
    private Long caseNoteId;

    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;

    @Column(name = "DATE_CREATION")
    private Timestamp creationDate;
    @Column(name = "TIME_CREATION")
    private Timestamp creationTime;

    @Column(name = "CONTACT_DATE")
    private Timestamp contactDate;
    @Column(name = "CONTACT_TIME")
    private Timestamp contactTime;

    @Column(name = "CASE_NOTE_TYPE")
    private String caseNoteType;
    @Column(name = "CASE_NOTE_SUB_TYPE")
    private String caseNoteSubType;

    @Column(name = "STAFF_ID")
    private Long staffId;
    @Column(name = "NOTE_SOURCE_CODE")
    private String noteSourceCode;

    @Column(name = "CASE_NOTE_TEXT")
    private String caseNoteText;
    @Column(name = "AMENDMENT_FLAG")
    private String amendmentFlag;
    @Column(name = "IWP_FLAG")
    private String iwpFlag;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "CHECK_BOX1")
    private String checkBox1;
    @Column(name = "CHECK_BOX2")
    private String checkBox2;
    @Column(name = "CHECK_BOX3")
    private String checkBox3;
    @Column(name = "CHECK_BOX4")
    private String checkBox4;
    @Column(name = "CHECK_BOX5")
    private String checkBox5;

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
