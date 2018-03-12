package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Charge {
    private Long chargeId;
    private Long bookingId;
    private Long offenderId;
    private String offenceCode;
    private String statuteCode;
    private Integer numberOfOffences;
    private LocalDate offenceDate;
    private LocalDate offenceRangeDate;
    private String pleaCode;
    private BigDecimal propertyValue;
    private BigDecimal totalPropertyValue;
    private Map<String, String> cjitOffenceCodes;
    private String chargeStatus;
    private List<String> resultCodes;
    @JsonProperty("case")
    private Case offenderCase;
    private Boolean mostSeriousCharge;
    private Integer chargeSequence;
    private Order order;
    private Integer lidsOffenceNumber;

}
