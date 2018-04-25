package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_IEP_LEVELS")
@IdClass(OffenderIepLevelPK.class)
public class OffenderIepLevel {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Id
    @Column(name = "IEP_LEVEL_SEQ")
    private Long iepLevelSeq;
    @Column(name = "IEP_DATE")
    private Timestamp iepDate;
    @Column(name = "IEP_TIME")
    private Timestamp iepTime;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "IEP_LEVEL")
    private String iepLevel;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "USER_ID")
    private String userId;
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
    @OneToOne
    @JoinColumns(
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            value = {
                    @JoinColumn(name = "AGY_LOC_ID", referencedColumnName = "AGY_LOC_ID", insertable = false, updatable = false),
                    @JoinColumn(name = "IEP_LEVEL", referencedColumnName = "IEP_LEVEL", insertable = false, updatable = false)
            })
    private IepLevel iepLevelThing;

}
