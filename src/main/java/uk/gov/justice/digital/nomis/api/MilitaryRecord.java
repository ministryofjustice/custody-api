package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MilitaryRecord {

    private Long bookingId;
    private Long militarySeq;

    private String warZoneCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String militaryDischargeCode;
    private String militaryBranchCode;
    private String description;
    private String unitNumber;
    private String enlistmentLocation;
    private String dischargeLocation;
    private String selectiveServicesFlag;
    private String militaryRankCode;
    private String serviceNumber;
    private String disciplinaryActionCode;
}
