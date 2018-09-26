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

    public uk.gov.justice.digital.nomis.api.OffenderImage offenderImageMetaDataOf(OffenderImage offenderImage) {
        return uk.gov.justice.digital.nomis.api.OffenderImage.builder()
            .activeFlag(typesTransformer.ynToBoolean(offenderImage.getActiveFlag()))
            .bookingId(offenderImage.getOffenderBookingId())
            .captureTime(typesTransformer.localDateTimeOf(offenderImage.getCaptureTime()))
            .createDatetime(typesTransformer.localDateTimeOf(offenderImage.getCreateDatetime()))
            .imageObjectId(offenderImage.getImageObjectId())
            .imageObjectSeq(offenderImage.getImageObjectSeq())
            .imageObjectType(offenderImage.getImageObjectType())
            .imageSourceCode(offenderImage.getImageSourceCode())
            .imageViewType(offenderImage.getImageViewType())
            .modifyDatetime(typesTransformer.localDateTimeOf(offenderImage.getModifyDatetime()))
            .offenderImageId(offenderImage.getOffenderImageId())
            .orientationType(offenderImage.getOrientationType())
            .build();
    }

    public byte[] thumbnailOf(OffenderImage offenderImage) {
        return offenderImage.getThumbnailImage();
    }
}