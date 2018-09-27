package uk.gov.justice.digital.nomis.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OffenderImage {
    private Long offenderImageId;
    private Long bookingId;
    private LocalDateTime captureTime;

    @ApiModelProperty(value = "NECK, KNEE, TORSO, FACE, DAMAGE, INJURY, HEAD, HAND, INCIDENT, ELBOW, FOOT, THIGH, ARM, ANKLE, FINGER, SHOULDER, EAR, TOE, FIGHT, FRONT, LEG, NOSE")
    private String orientationType;

    @ApiModelProperty(value="FACE, INCIDENT or IDENTIFYING_MARKS")
    private String imageObjectType;

    @ApiModelProperty(value="INCIDENT, FACE, TATTOO, MARK, SCAR, OTHER")
    private String imageViewType;

    private Long imageObjectId;
    private Long imageObjectSeq;
    private Boolean activeFlag;
    private LocalDateTime createDatetime;
    private LocalDateTime modifyDatetime;
    private String imageSourceCode;

}
