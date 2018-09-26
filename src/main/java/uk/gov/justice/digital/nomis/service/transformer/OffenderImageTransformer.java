package uk.gov.justice.digital.nomis.service.transformer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderImage;

@Component
@Slf4j
public class OffenderImageTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderImageTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public uk.gov.justice.digital.nomis.api.OffenderImage offenderOf(OffenderImage offender) {
        return uk.gov.justice.digital.nomis.api.OffenderImage.builder()
            .activeFlag(typesTransformer.ynToBoolean(offender.getActiveFlag()))
            .bookingId(offender.getOffenderBookingId())
            .captureTime(typesTransformer.localDateTimeOf(offender.getCaptureTime()))
            .createDatetime(typesTransformer.localDateTimeOf(offender.getCreateDatetime()))
            .imageObjectId(offender.getImageObjectId())
            .imageObjectSeq(offender.getImageObjectSeq())
            .imageObjectType(offender.getImageObjectType())
            .imageSourceCode(offender.getImageSourceCode())
            .imageViewType(offender.getImageViewType())
            .modifyDatetime(typesTransformer.localDateTimeOf(offender.getModifyDatetime()))
            .offenderImageId(offender.getOffenderImageId())
            .orientationType(offender.getOrientationType())
            .build();
    }

}