package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OffenderImage {
    private Long offenderImageId;
    private Long bookingId;
    private LocalDateTime captureTime;
    private String orientationType;
    private String imageObjectType;
    private String imageViewType;
    private Long imageObjectId;
    private Long imageObjectSeq;
    private Boolean activeFlag;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;
    private String imageSourceCode;

}
