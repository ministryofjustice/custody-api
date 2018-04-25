package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "IEP_LEVELS")
@IdClass(IepLevelPK.class)
public class IepLevel {
    @Id
    @Column(name = "IEP_LEVEL")
    private String iepLevel;
    @Id
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Basic
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Basic
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Basic
    @Column(name = "USER_ID")
    private String userId;
    @Basic
    @Column(name = "DEFAULT_FLAG")
    private String defaultFlag;
    @Basic
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Basic
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Basic
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Basic
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Basic
    @Column(name = "REMAND_TRANSFER_LIMIT")
    private BigDecimal remandTransferLimit;
    @Basic
    @Column(name = "REMAND_SPEND_LIMIT")
    private BigDecimal remandSpendLimit;
    @Basic
    @Column(name = "CONVICTED_TRANSFER_LIMIT")
    private BigDecimal convictedTransferLimit;
    @Basic
    @Column(name = "CONVICTED_SPEND_LIMIT")
    private BigDecimal convictedSpendLimit;
    @Basic
    @Column(name = "RECORD_USER_ID")
    private String recordUserId;
    @Basic
    @Column(name = "AUDIT_TIMESTAMP")
    private Timestamp auditTimestamp;
    @Basic
    @Column(name = "AUDIT_USER_ID")
    private String auditUserId;
    @Basic
    @Column(name = "AUDIT_MODULE_NAME")
    private String auditModuleName;
    @Basic
    @Column(name = "AUDIT_CLIENT_USER_ID")
    private String auditClientUserId;
    @Basic
    @Column(name = "AUDIT_CLIENT_IP_ADDRESS")
    private String auditClientIpAddress;
    @Basic
    @Column(name = "AUDIT_CLIENT_WORKSTATION_NAME")
    private String auditClientWorkstationName;
    @Basic
    @Column(name = "AUDIT_ADDITIONAL_INFO")
    private String auditAdditionalInfo;

}
