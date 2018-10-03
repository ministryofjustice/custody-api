package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "COURSE_SCHEDULES")
public class CourseSchedule implements Serializable {
    @Id
    @Column(name = "CRS_SCH_ID")
    private Long crsSchId;
    @Column(name = "CRS_ACTY_ID")
    private Long crsActyId;
    @Column(name = "WEEKDAY")
    private String weekday;
    @Column(name = "SCHEDULE_DATE")
    private Timestamp scheduleDate;
    @Column(name = "START_TIME")
    private Timestamp startTime;
    @Column(name = "END_TIME")
    private Timestamp endTime;
    @Column(name = "SESSION_NO")
    private Integer sessionNo;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "SCHEDULE_STATUS")
    private String scheduleStatus;
    @Column(name = "CATCH_UP_CRS_SCH_ID")
    private Long catchUpCrsSchId;
    @Column(name = "VIDEO_REFERENCE_ID")
    private String videoReferenceId;
    @Column(name = "SLOT_CATEGORY_CODE")
    private String slotCategoryCode;

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
