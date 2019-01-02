package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDetail {

    /*
    145a    Diary Details - Date (Movement)
   145b    Diary Details - Time (Movement)
   145c    Diary Details - Movement Reason Code
   145d    Diary Details - Movement Comment Text
   145e    Diary Details - Escort Type
   145f    Diary Details - Not For Release Alert
     */

    private Long bookingId;
    private LocalDateTime movementDateTime;
    private String movementReasonCode;
    private String movementReasonDescription;
    private String movementReasonType;
    private String comments;
    private String escortType;
    private Boolean notForReleaseAlert;

}
