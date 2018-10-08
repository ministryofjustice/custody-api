package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_MILITARY_RECORDS")
public class OffenderMilitaryRecord {

    @Embeddable
    @Data
    public static class Pk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "OFFENDER_BOOK_ID")
        private OffenderBooking offenderBooking;

        @Column(name = "MILITARY_SEQ")
        private Long militarySeq;
    }

    @EmbeddedId
    private Pk id;

    @Column(name = "WAR_ZONE_CODE")
    private String warZoneCode;
    @Column(name = "START_DATE")
    private Timestamp startDate;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "MILITARY_DISCHARGE_CODE")
    private String militaryDischargeCode;
    @Column(name = "MILITARY_BRANCH_CODE")
    private String militaryBranchCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "UNIT_NUMBER")
    private String unitNumber;
    @Column(name = "ENLISTMENT_LOCATION")
    private String enlistmentLocation;
    @Column(name = "DISCHARGE_LOCATION")
    private String dischargeLocation;
    @Column(name = "SELECTIVE_SERVICES_FLAG")
    private String selectiveServicesFlag;
    @Column(name = "MILITARY_RANK_CODE")
    private String militaryRankCode;
    @Column(name = "SERVICE_NUMBER")
    private String serviceNumber;
    @Column(name = "DISCIPLINARY_ACTION_CODE")
    private String disciplinaryActionCode;

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
}
