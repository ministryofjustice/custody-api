package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "programProfileId")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
