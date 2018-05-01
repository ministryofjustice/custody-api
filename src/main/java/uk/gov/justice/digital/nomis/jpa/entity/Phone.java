package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "PHONES")
@Inheritance
@DiscriminatorColumn(name = "OWNER_CLASS") // OFF,PER,CORP,STF,OFF_EDU,OFF_EMP,AGY,PER_EMP,ADDR
public abstract class Phone {
    @Id
    @Column(name = "PHONE_ID")
    private Long phoneId;
    @Column(name = "OWNER_CLASS", insertable = false, updatable = false)
    private String ownerClass;
    @Column(name = "OWNER_ID")
    private Integer ownerId;
    @Column(name = "OWNER_SEQ")
    private Integer ownerSeq;
    @Column(name = "OWNER_CODE")
    private String ownerCode;
    @Column(name = "PHONE_TYPE")
    private String phoneType;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "EXT_NO")
    private String extNo;
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

    public abstract String getType();

    public abstract IdPair getRelationship();

}
