package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MilitaryRecord {

    private Long bookingId;
    private Long militarySeq;

    private KeyValue warZoneCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String unitNumber;
    private String serviceNumber;
    private String enlistmentLocation;
    private String dischargeLocation;
    private Boolean selectiveServices;
    private KeyValue militaryDischargeCode;
    private KeyValue militaryBranchCode;
    private KeyValue militaryRankCode;
    private KeyValue disciplinaryActionCode;
}
