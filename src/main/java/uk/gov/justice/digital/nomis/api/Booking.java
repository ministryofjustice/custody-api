package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Builder
public class Booking {
    private Long offenderBookingId;
    private String bookingNo;
    private Long offenderId;
    private Long rootOffenderId;
    private String agencyLocationId;
    private Long livingUnitId;
    private Boolean activeFlag;
    private String bookingStatus;
    private String inOutStatus;
    private String statusReason;
    private LocalDate startDate;
    private Optional<LocalDate> endDate;
    private Long bookingSequence;
}
