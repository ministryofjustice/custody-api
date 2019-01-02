package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndividualSchedule {
    public Long eventId;
    public String eventType;
    public String eventSubType;
    public String eventStatus;
    public LocalDateTime eventStartDateTime;
    public LocalDateTime eventEndDateTime;
    public Long bookingId;
    public LocalDateTime returnDateTime;
    public String comments;
    public KeyValue escortType;
}
