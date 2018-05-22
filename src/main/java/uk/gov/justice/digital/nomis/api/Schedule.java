package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class Schedule {
    private Long courseScheduleId;
    private Integer sessionNo;
    private LocalDate scheduledDate;
    private String scheduleDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long catchUpCourseScheduleId;
}
