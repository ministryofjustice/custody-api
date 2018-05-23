package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Alert {
    private Long bookingId;
    private Integer alertSeq;
    private Long rootOffenderId;
    private String caseloadId;
    private String caseloadType;
    private LocalDate alertDate;
    private String alertType;
    private String alertCode;
    private String authorizePersonText;
    private String alertStatus;
    private Boolean verified;
    private LocalDate expiryDate;
    private String comments;
}
