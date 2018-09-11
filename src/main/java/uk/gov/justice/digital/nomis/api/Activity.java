package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Activity {
    private Long courseActivityId;
    private String description;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;
    private Boolean active;
    private Boolean outsideWork;

    private List<Schedule> schedules;

}
