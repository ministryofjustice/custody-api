package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENCE_RESULT_CODES")
public class OffenceResultCode {
    @Id
    @Column(name = "RESULT_CODE")
    private String resultCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DISPOSITION_CODE")
    private String dispositionCode;
    @Column(name = "CHARGE_STATUS")
    private String chargeStatus;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CONVICTION_FLAG")
    private String convictionFlag;
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
    @Column(name = "LIST_SEQ")
    private Integer listSeq;

}
