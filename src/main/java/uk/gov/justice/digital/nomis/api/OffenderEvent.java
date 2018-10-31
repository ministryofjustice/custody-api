package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class OffenderEvent {
    private String eventId;
    private String eventType;
    private LocalDateTime eventDatetime;

    private Long rootOffenderId;
    private Long offenderId;
    private Long aliasOffenderId;
    private Long previousOffenderId;
    private String offenderIdDisplay;

    private Long bookingId;
    private String bookingNumber;
    private String previousBookingNumber;

    private Long sanctionSeq;
    private Long movementSeq;
    private Long imprisonmentStatusSeq;
    private Long assessmentSeq;

    private Long alertSeq;
    private LocalDateTime alertDateTime;
    private String alertType;
    private String alertCode;
    private LocalDateTime expiryDateTime;


    private String agencyLocationId;
    private Long riskPredictorId;
    private Long addressId;
    private Long personId;
    private Long sentenceCalculationId;
    private Long oicHearingId;
    private Long oicOffenceId;
    private Long pleaFindingCode;
    private Long findingCode;

    private Long resultSeq;
    private Long agencyIncidentId;
    private Long chargeSeq;

    private String identifierType;
    private String identifierValue;

    private Long ownerId;
    private String ownerClass;

    private Long sentenceSeq;
    private String conditionCode;
    private Long offenderSentenceConditionId;


    private LocalDate addressEndDate;
    private String primaryAddressFlag;
    private String mailAddressFlag;

    private String addressUsage;

    // external movement event data
    private LocalDateTime movementDateTime;
    private String movementType;
    private String movementReasonCode;
    private String directionCode;
    private String escortCode;
    private Long fromAgencyLocationId;
    private Long toAgencyLocationId;

    private String nomisEventType;
}
