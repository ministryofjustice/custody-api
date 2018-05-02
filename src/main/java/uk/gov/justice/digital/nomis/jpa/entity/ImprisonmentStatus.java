package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "IMPRISONMENT_STATUSES")
public class ImprisonmentStatus {
    @Id
    @Column(name = "IMPRISONMENT_STATUS_ID")
    private Long imprisonmentStatusId;
    @Column(name = "IMPRISONMENT_STATUS")
    private String imprisonmentStatus;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BAND_CODE")
    private String bandCode;
    @Column(name = "RANK_VALUE")
    private Integer rankValue;
    @Column(name = "IMPRISONMENT_STATUS_SEQ")
    private Integer imprisonmentStatusSeq;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
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
