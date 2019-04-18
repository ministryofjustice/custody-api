package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class ActivityDetailCDE {
    String description;
    AgencyInternalLocation livingUnit;
    LocalDate eventDate;
    LocalTime startTime;
    LocalTime endTime;
}
