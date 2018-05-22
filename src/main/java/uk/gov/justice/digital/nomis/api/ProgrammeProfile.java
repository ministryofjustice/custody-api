package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProgrammeProfile {
    private Long bookingId;
    private Long programProfileId;
    private Activity courseActivity;
    private String agencyLocationId;
    private String offenderProgramStatus;
    private Integer startSessionNo;
    private LocalDate offenderStartDate;
    private LocalDate offenderEndDate;
    private Boolean suspended;
}
