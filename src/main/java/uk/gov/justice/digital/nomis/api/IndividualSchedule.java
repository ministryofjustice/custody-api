package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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
