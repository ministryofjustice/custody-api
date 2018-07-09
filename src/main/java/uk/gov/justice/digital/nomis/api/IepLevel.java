package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
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
