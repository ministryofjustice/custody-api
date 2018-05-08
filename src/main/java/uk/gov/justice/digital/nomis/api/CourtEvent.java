package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CourtEvent {
    private Long eventId;
    private Long caseId;
    private Long bookingId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String courtEventType;
    private String judgeName;
    private String eventStatus;
    private Long parentEventId;
    private String agyLocId;
    private String outcomeReasonCode;
    private String comments;
    private String eventOutcome;
    private Boolean nextEventRequest;
    private Boolean orderRequested;
    private String resultCode;
    private LocalDateTime nextEventDateTime;
    private LocalDate outcomeDate;
    private Long offenderProceedingId;
    private String directionCode;
    private Boolean hold;
    private List<Charge> courtEventCharges;
}
