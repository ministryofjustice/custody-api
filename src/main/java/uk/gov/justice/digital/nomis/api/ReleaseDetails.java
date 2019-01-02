package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseDetails {
    private Long bookingId;
    private LocalDate releaseDate;
    private String comments;
    private String movementReasonType;
    private String movementReasonCode;
    private String movementReasonDescription;
    private Long eventId;
    private String eventStatus;
    private LocalDate approvedReleaseDate;
    private LocalDate autoReleaseDate;
    private LocalDate dtoApprovedDate;
    private LocalDate dtoMidTermDate;
    private Boolean verified;
}
