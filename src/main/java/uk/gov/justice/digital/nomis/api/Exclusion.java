package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exclusion {
    private Long offenderExcludeActSchdId;
    private Long bookingId;
    private Long offenderProgramProfileId;
    private String slotCategoryCode;
    private String excludeDay;
    private Activity courseActivity;
}
