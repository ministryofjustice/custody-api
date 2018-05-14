package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.DiaryDetail;
import uk.gov.justice.digital.nomis.jpa.entity.CourtEvent;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIndSchedule;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderReleaseDetails;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCode;

import java.util.Optional;

@Component
public class DiaryDetailTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public DiaryDetailTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public DiaryDetail diaryDetailOf(CourtEvent courtEvent) {
        return DiaryDetail.builder()
                .bookingId(courtEvent.getOffenderBookId())
                .movementDateTime(typesTransformer.localDateTimeOf(courtEvent.getEventDate(), courtEvent.getStartTime()))
                .movementReasonCode(courtEvent.getCourtEventType())
                .comments(courtEvent.getCommentText())
                .build();
    }

    public DiaryDetail diaryDetailOf(OffenderIndSchedule offenderIndSchedule, ReferenceCode escort) {
        return DiaryDetail.builder()
                .bookingId(offenderIndSchedule.getOffenderBookId())
                .movementDateTime(typesTransformer.localDateTimeOf(offenderIndSchedule.getEventDate(), offenderIndSchedule.getStartTime()))
                .movementReasonCode(offenderIndSchedule.getEventSubType())
                .comments(offenderIndSchedule.getCommentText())
                .escortType(Optional.ofNullable(escort).map(e -> e.getDescription()).orElse(null))
                .build();
    }

    public DiaryDetail diaryDetailOf(OffenderReleaseDetails offenderReleaseDetails) {
        return DiaryDetail.builder()
                .bookingId(offenderReleaseDetails.getOffenderBookId())
                .movementDateTime(typesTransformer.localDateTimeOf(offenderReleaseDetails.getReleaseDate()))
                .movementReasonCode(offenderReleaseDetails.getMovementReasonCode())
                .comments(offenderReleaseDetails.getCommentText())
                .build();
    }
}
