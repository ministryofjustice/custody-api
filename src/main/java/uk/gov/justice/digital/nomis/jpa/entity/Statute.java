package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "STATUTES")
public class Statute {
    @Id
    @Column(name = "STATUTE_CODE")
    private String statuteCode;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "LEGISLATING_BODY_CODE")
    private String legislatingBodyCode;
    @Basic
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Basic
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Basic
    @Column(name = "UPDATE_ALLOWED_FLAG")
    private String updateAllowedFlag;
    @Basic
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Basic
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
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
