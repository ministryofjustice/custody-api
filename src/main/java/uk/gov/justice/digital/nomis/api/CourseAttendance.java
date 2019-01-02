package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseAttendance {
    private Long eventId;
    private Long bookingId;
    private Schedule courseSchedule;
    private Activity courseActivity;
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String eventSubType;
    private String eventStatus;
    private String comments;
    private String hiddenComments;
    private AgencyInternalLocation toInternalLocation;
    private String outcomeReasonCode;
    private BigDecimal pieceWork;
    private String engagementCode;
    private String understandingCode;
    private String details;
    private BigDecimal creditedHours;
    private BigDecimal agreedTravelHour;
    private String supervisorName;
    private Long supervisorStaffId;
    private String behaviourCode;
    private String actionCode;
    private LocalDate sickNoteReceivedDate;
    private LocalDate sickNoteExpiryDate;
    private Long offenderProgramProfileId;
    private LocalTime inTime;
    private LocalTime outTime;
    private String performanceCode;
    private Long referenceId;
    private String toAddressOwnerClass;
    private Long toAddressId;
    private String eventOutcome;
    private Long crsApptId;
    private Long offenderCourseApptRuleId;
    private String eventType;
    private AgencyLocation agencyLocation;
    private AgencyLocation toAgencyLocation;
    private Boolean unexcusedAbsence;
    private Integer sessionNo;
    private Long offenderPrgObligationId;
    private Long programId;
    private BigDecimal bonusPay;
    private String payFlag;
    private Boolean authorisedAbsence;

}
