package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExternalMovement {
    private Long bookingId;
    private Long offenderId;
    private Long sequenceNumber;
    private LocalDateTime movementDateTime;
    private String movementTypeCode;
    private String movementReasonCode;
    private String movementReasonDescription;
    private String description;
    private String movementDirection;
    private Boolean active;
    private String fromAgencyLocationId;
    private String toAgencyLocationId;
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
    private KeyValue escortCode;
    private String escortText;
    private LocalDateTime reportingDateTime;
    private LocalDateTime applicationDateTime;
    private String toCityCode;
    private String fromCityCode;
    private String toCountryCode;
    private String ojLocationCode;

}
