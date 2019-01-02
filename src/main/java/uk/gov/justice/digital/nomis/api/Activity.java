package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(of = "courseActivityId")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Long courseActivityId;
    private String description;
    private LocalDate scheduledStartDate;
    private LocalDate scheduledEndDate;
    private Boolean active;
    private Boolean outsideWork;
    private AgencyLocation agencyLocation;
    private AgencyInternalLocation livingUnit;

    private List<Schedule> schedules;

}
