package uk.gov.justice.digital.nomis.service.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.OffenderEvent;

import java.io.IOException;
import java.util.Optional;

@Component
public class OffenderEventsTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderEventsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }
    public OffenderEvent offenderEventOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent offenderEvent) {
        return Optional.ofNullable(offenderEvent)
                .map(event -> OffenderEvent.builder()
                        .eventId(event.getEventId())
                        .eventDatetime(typesTransformer.localDateTimeOf(event.getEventTimestamp()))
                        .eventType(withEventType(event))
                        .rootOffenderId(event.getRootOffenderId())
                        .offenderIdDisplay(event.getOffenderIdDisplay())
                        .agencyLocId(event.getAgencyLocId())
                        .build()).orElse(null);
    }

    private String withEventType(uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent event) {
        if (event.getEventType().equalsIgnoreCase("CASE_NOTE")) {
            try {
                JsonNode json = new ObjectMapper().readTree(event.getEventData1());

                return String.format("%s-%s", json.get("case_note").get("type").get("code").asText(), json.get("case_note").get("sub_type").get("code").asText());
            } catch (IOException e) {
                System.err.println(e.getStackTrace());
            }
        }

        return event.getEventType();
    }
}
