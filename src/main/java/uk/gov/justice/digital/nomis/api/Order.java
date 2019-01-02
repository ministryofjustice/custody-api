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
public class Order {
    private Long orderId;
    private Long bookingId;
    private LocalDate courtDate;
    private String orderType;
    private String issuingAgencyLocationId;
    private String orderStatus;
    private LocalDate dueDate;
    private String courtSeriousnessLevel;
    private String orderSeriousnessLevel;
    private LocalDate requestDate;
    private Integer courtEventId;
    private LocalDate completeDate;
    private String interventionTierCode;
    private LocalDate issueDate;
    private String comments;

}
