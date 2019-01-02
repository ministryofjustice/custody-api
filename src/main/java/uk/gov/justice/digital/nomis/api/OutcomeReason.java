package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeReason {
    private String Code;
    private String Description;

    private String DispositionCode;
    private Boolean ChargeStatus;
    private Boolean Conviction;
    private Boolean Active;
    private LocalDateTime ExpiryDate;

}
