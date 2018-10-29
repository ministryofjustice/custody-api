package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "API_OFFENDER_EVENTS", schema = "API_OWNER")
public class OffenderEvent {

    @Id
    @Column(name = "API_EVENT_ID")
    private Long eventId;
    @Column(name = "EVENT_TYPE")
    private String eventType;
    @Column(name = "EVENT_TIMESTAMP")
    private Timestamp eventTimestamp;

    @Column(name = "ROOT_OFFENDER_ID")
    private Long rootOffenderId;
    @Column(name = "NOMS_ID")
    private String offenderIdDisplay;

    @Column(name = "AGY_LOC_ID")
    private String agencyLocId;

    @Column(name = "EVENT_DATA_1", length = 4000)
    private String eventData1;
    @Column(name = "EVENT_DATA_2", length = 4000)
    private String eventData2;
    @Column(name = "EVENT_DATA_3", length = 4000)
    private String eventData3;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
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
