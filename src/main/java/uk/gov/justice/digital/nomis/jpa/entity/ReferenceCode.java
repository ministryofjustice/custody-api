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
@Table(name = "REFERENCE_CODES")
@IdClass(ReferenceCodePK.class)
public class ReferenceCode {
    @Id
    @Column(name = "DOMAIN")
    private String domain;
    @Id
    @Column(name = "CODE")
    private String code;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "SYSTEM_DATA_FLAG")
    private String systemDataFlag;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "EXPIRED_DATE")
    private Timestamp expiredDate;
    @Column(name = "NEW_CODE")
    private String newCode;
    @Column(name = "PARENT_CODE")
    private String parentCode;
    @Column(name = "PARENT_DOMAIN")
    private String parentDomain;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
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
