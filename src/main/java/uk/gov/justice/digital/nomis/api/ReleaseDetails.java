package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReleaseDetails {
    private Long bookingId;
    private LocalDate releaseDate;
    private String comments;
    private String movementType;
    private String movementReasonCode;
    private Long eventId;
    private String eventStatus;
    private LocalDate approvedReleaseDate;
    private LocalDate autoReleaseDate;
    private LocalDate dtoApprovedDate;
    private LocalDate dtoMidTermDate;
    private Boolean verified;
}
