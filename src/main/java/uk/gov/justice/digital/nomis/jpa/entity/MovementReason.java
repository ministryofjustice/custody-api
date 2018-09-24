package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "MOVEMENT_REASONS")
@IdClass(MovementReasonPK.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementReason {
    @Id
    @Column(name = "MOVEMENT_TYPE")
    private String movementType;
    @Id
    @Column(name = "MOVEMENT_REASON_CODE")
    private String movementReasonCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "OPEN_CONTACT_FLAG")
    private String openContactFlag;
    @Column(name = "CLOSE_CONTACT_FLAG")
    private String closeContactFlag;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "UPDATE_ALLOWED_FLAG")
    private String updateAllowedFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "NOTIFICATION_TYPE")
    private String notificationType;
    @Column(name = "NOTIFICATION_FLAG")
    private String notificationFlag;
    @Column(name = "BILLING_SERVICE_FLAG")
    private String billingServiceFlag;
    @Column(name = "TRANSPORTATION_FLAG")
    private String transportationFlag;
    @Column(name = "HEADER_STATUS_FLAG")
    private String headerStatusFlag;
    @Column(name = "IN_MOVEMENT_TYPE")
    private String inMovementType;
    @Column(name = "IN_MOVEMENT_REASON_CODE")
    private String inMovementReasonCode;
    @Column(name = "ESC_RECAP_FLAG")
    private String escRecapFlag;
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
    @Column(name = "UNEMPLOYMENT_PAY")
    private String unemploymentPay;

}
