package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExternalMovement {
    private Long offenderBookingId;
    private Long offenderId;
    private Long sequenceNumber;
    private LocalDateTime movementDateTime;
    private String movementTypeCode;
    private String movementReasonCode;
    private String description;
    private String movementDirection;
    private String active;
    private String fromAgencyLocationId;
    private String toAgencyCodeLocationId;
    private Long fromAddressId;
    private Long toAddressId;
    private String comments;
    private Boolean unemploymentPay;
    private Boolean escapeRecapture;
    private String inMovementType;
    private String inMovementReasonCode;
    private Boolean transportation;
    private String internalScheduleType;
    private String internalScheduleReasonCode;
    private String arrestAgencyLocationId;
    private String toProvStatCode;
    private String escortCode;
    private String escortText;
    private LocalDateTime reportingDateTime;
    private String toCityCode;
    private String fromCityCode;
    private String toCountryCode;
    private String ojLocationCode;

}
