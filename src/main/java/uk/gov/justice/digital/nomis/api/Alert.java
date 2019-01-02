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
public class Alert {
    private Long bookingId;
    private Integer alertSeq;
    private Long offenderId;
    private String caseloadId;
    private String caseloadType;
    private LocalDate alertDate;
    private LocalDate createdDate;
    private LocalDate expiryDate;
    private String alertType;
    private KeyValue alertCode;
    private String authorizePersonText;
    private String alertStatus;
    private Boolean verified;
    private String comments;
}
