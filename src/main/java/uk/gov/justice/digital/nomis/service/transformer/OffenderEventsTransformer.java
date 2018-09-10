package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.OffenderEvent;

import java.util.Optional;

@Component
public class OffenderEventsTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderEventsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }
    public OffenderEvent offenderEventOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent offenderEvent) {
        return Optional.ofNullable(offenderEvent).map(
                event -> OffenderEvent.builder()
                        .eventId(event.getEventId())
                        .eventDatetime(typesTransformer.localDateTimeOf(event.getEventTimestamp()))
                        .eventType(event.getEventType())
                        .rootOffenderId(event.getRootOffenderId())
                        .offenderIdDisplay(event.getOffenderIdDisplay())
                        .agencyLocId(event.getAgencyLocId())
                        .build()).orElse(null);
    }
}
