package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "programProfileId")
@Builder
public class ProgrammeProfile {
    private Long bookingId;
    private Long programProfileId;
    private Activity courseActivity;
    private AgencyLocation agencyLocation;
    private String offenderProgramStatus;
    private Integer startSessionNo;
    private LocalDate offenderStartDate;
    private LocalDate offenderEndDate;
    private Boolean suspended;
}
