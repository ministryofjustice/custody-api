package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.ReleaseDetails;
import uk.gov.justice.digital.nomis.jpa.entity.MovementReason;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderReleaseDetails;

@Component
public class ReleaseDetailsTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public ReleaseDetailsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public ReleaseDetails releaseDetailsOf(OffenderReleaseDetails ord) {

        MovementReason movementReason = ord.getMovementReason();

        return ReleaseDetails.builder()
                .approvedReleaseDate(typesTransformer.localDateOf(ord.getApprovedReleaseDate()))
                .autoReleaseDate(typesTransformer.localDateOf(ord.getAutoReleaseDate()))
                .bookingId(ord.getOffenderBookId())
                .comments(ord.getCommentText())
                .dtoApprovedDate(typesTransformer.localDateOf(ord.getDtoApprovedDate()))
                .dtoMidTermDate(typesTransformer.localDateOf(ord.getDtoMidTermDate()))
                .eventId(ord.getEventId())
                .eventStatus(ord.getEventStatus())
                .movementReasonCode(movementReason.getMovementReasonCode())
                .movementReasonDescription(movementReason.getDescription())
                .movementType(movementReason.getMovementType())
                .releaseDate(typesTransformer.localDateOf(ord.getReleaseDate()))
                .verified(typesTransformer.ynToBoolean(ord.getVerifiedFlag()))
                .build();
    }
}