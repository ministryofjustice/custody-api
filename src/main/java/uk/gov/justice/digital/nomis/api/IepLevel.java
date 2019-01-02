package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IepLevel {
    private KeyValue iepLevel;
    private String agencyLocationId;
    private Boolean active;
    private LocalDateTime expiryDate;
    private Boolean isDefault;
    private BigDecimal remandTransferLimit;
    private BigDecimal remandSpendLimit;
    private BigDecimal convictedTransferLimit;
    private BigDecimal convictedSpendLimit;
}
