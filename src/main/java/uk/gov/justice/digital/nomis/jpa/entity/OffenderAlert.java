package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_ALERTS")
@IdClass(OffenderAlertPK.class)
public class OffenderAlert {
    @Column(name = "ALERT_DATE")
    private Timestamp alertDate;
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "ROOT_OFFENDER_ID")
    private Long rootOffenderId;
    @Id
    @Column(name = "ALERT_SEQ")
    private Integer alertSeq;
    @Column(name = "ALERT_TYPE")
    private String alertType;
    @Column(name = "ALERT_CODE")
    private String alertCode;
    @Column(name = "AUTHORIZE_PERSON_TEXT")
    private String authorizePersonText;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "ALERT_STATUS")
    private String alertStatus;
    @Column(name = "VERIFIED_FLAG")
    private String verifiedFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "CASELOAD_ID")
    private String caseloadId;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
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
