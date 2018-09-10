package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OffenderEvent {
    private Long eventId;
    private String eventType;
    private LocalDateTime eventDatetime;
    private Long rootOffenderId;
    private String offenderIdDisplay;
    private String agencyLocId;
}
