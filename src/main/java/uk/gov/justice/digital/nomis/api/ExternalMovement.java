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
    private AgencyLocation fromAgencyLocation;
    private AgencyLocation toAgencyLocation;
    private Long fromAddressId;
    private String fromCityCode;
    private Long toAddressId;
    private String toCityCode;
    private String toCountryCode;
    private String toProvStatCode;
    private String comments;
    private Boolean unemploymentPay;
    private Boolean escapeRecapture;
    private String inMovementType;
    private String inMovementReasonCode;
    private Boolean transportation;
    private String internalScheduleType;
    private String internalScheduleReasonCode;
    private String arrestAgencyLocationId;
    private KeyValue escortCode;
    private String escortText;
    private LocalDateTime reportingDateTime;
    private LocalDateTime applicationDateTime;
    private String ojLocationCode;

}
