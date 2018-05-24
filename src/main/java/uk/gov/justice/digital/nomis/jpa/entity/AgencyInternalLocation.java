package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "AGENCY_INTERNAL_LOCATIONS")
public class AgencyInternalLocation {
    @Id
    @Column(name = "INTERNAL_LOCATION_ID")
    private Long internalLocationId;
    @Column(name = "INTERNAL_LOCATION_CODE")
    private String internalLocationCode;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "INTERNAL_LOCATION_TYPE")
    private String internalLocationType;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SECURITY_LEVEL_CODE")
    private String securityLevelCode;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "PARENT_INTERNAL_LOCATION_ID")
    private Long parentInternalLocationId;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "CNA_NO")
    private Long cnaNo;
    @Column(name = "CERTIFIED_FLAG")
    private String certifiedFlag;
    @Column(name = "DEACTIVATE_DATE")
    private Timestamp deactivateDate;
    @Column(name = "REACTIVATE_DATE")
    private Timestamp reactivateDate;
    @Column(name = "DEACTIVATE_REASON_CODE")
    private String deactivateReasonCode;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "USER_DESC")
    private String userDesc;
    @Column(name = "ACA_CAP_RATING")
    private Long acaCapRating;
    @Column(name = "UNIT_TYPE")
    private String unitType;
    @Column(name = "OPERATION_CAPACITY")
    private Integer operationCapacity;
    @Column(name = "NO_OF_OCCUPANT")
    private Integer noOfOccupant;
    @Column(name = "TRACKING_FLAG")
    private String trackingFlag;
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
