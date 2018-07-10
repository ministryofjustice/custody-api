package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_EMPLOYMENTS")
@IdClass(OffenderEmploymentPK.class)
public class OffenderEmployment {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Id
    @Column(name = "EMPLOY_SEQ")
    private Long employSeq;
    @Column(name = "EMPLOYMENT_DATE")
    private Timestamp employmentDate;
    @Column(name = "EMPLOYMENT_POST_CODE")
    private String employmentPostCode;
    @Column(name = "EMPLOYMENT_TYPE")
    private String employmentType;
    @Column(name = "TERMINATION_DATE")
    private Timestamp terminationDate;
    @Column(name = "EMPLOYER_NAME")
    private String employerName;
    @Column(name = "SUPERVISOR_NAME")
    private String supervisorName;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "TERMINATION_REASON_TEXT")
    private String terminationReasonText;
    @Column(name = "WAGE")
    private BigDecimal wage;
    @Column(name = "WAGE_PERIOD_CODE")
    private String wagePeriodCode;
    @Column(name = "OCCUPATIONS_CODE")
    private String occupationsCode;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "ROOT_OFFENDER_ID")
    private Integer rootOffenderId;
    @Column(name = "CONTACT_TYPE")
    private String contactType;
    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;
    @Column(name = "SCHEDULE_TYPE")
    private String scheduleType;
    @Column(name = "SCHEDULE_HOURS")
    private Integer scheduleHours;
    @Column(name = "HOURS_WEEK")
    private Integer hoursWeek;
    @Column(name = "PARTIAL_EMPLOYMENT_DATE_FLAG")
    private String partialEmploymentDateFlag;
    @Column(name = "PARTIAL_TERMINATION_DATE_FLAG")
    private String partialTerminationDateFlag;
    @Column(name = "CHECK_BOX_1")
    private String checkBox1;
    @Column(name = "CHECK_BOX_2")
    private String checkBox2;
    @Column(name = "EMPLOYER_AWARE_FLAG")
    private String employerAwareFlag;
    @Column(name = "CONTACT_EMPLOYER_FLAG")
    private String contactEmployerFlag;
    @Column(name = "OFFENDER_EMPLOYMENT_ID")
    private Long offenderEmploymentId;
    @Column(name = "EMPLOYMENT_SCHEDULE")
    private String employmentSchedule;
    @Column(name = "CERTIFICATION")
    private String certification;

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
