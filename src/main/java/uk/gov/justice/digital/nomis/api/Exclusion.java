package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Exclusion {
    private Long offenderExcludeActSchdId;
    private Long bookingId;
    private Long offenderProgramProfileId;
    private String slotCategoryCode;
    private String excludeDay;
    private Activity courseActivity;
}
