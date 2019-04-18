package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(of = "courseScheduleId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    private Long courseScheduleId;
    private Integer sessionNo;
    private LocalDate scheduledDate;
    private String scheduleDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long catchUpCourseScheduleId;
    private String slotCategoryCode;
}
