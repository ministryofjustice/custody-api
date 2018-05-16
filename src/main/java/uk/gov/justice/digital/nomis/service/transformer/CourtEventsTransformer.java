package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.CourtEvent;
import uk.gov.justice.digital.nomis.api.Sentence;
import uk.gov.justice.digital.nomis.jpa.entity.CourtEventCharge;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourtEventsTransformer {

    private final TypesTransformer typesTransformer;
    private final ChargesTransformer chargesTransformer;
    private final SentenceTransformer sentenceTransformer;

    @Autowired
    public CourtEventsTransformer(TypesTransformer typesTransformer, ChargesTransformer chargesTransformer, SentenceTransformer sentenceTransformer) {
        this.typesTransformer = typesTransformer;
        this.chargesTransformer = chargesTransformer;
        this.sentenceTransformer = sentenceTransformer;
    }

    public CourtEvent courtEventOf(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent courtEvent) {
        return CourtEvent.builder()
                .agyLocId(courtEvent.getAgyLocId())
                .bookingId(courtEvent.getOffenderBookId())
                .caseId(courtEvent.getCaseId())
                .comments(courtEvent.getCommentText())
                .courtEventCharges(courtEventChargesOf(courtEvent.getCourtEventCharges()))
                .courtEventType(courtEvent.getCourtEventType())
                .directionCode(courtEvent.getDirectionCode())
                .startDateTime(typesTransformer.localDateTimeOf(courtEvent.getEventDate(), courtEvent.getStartTime()))
                .endDateTime(typesTransformer.localDateTimeOf(courtEvent.getEventDate(), courtEvent.getEndTime()))
                .eventId(courtEvent.getEventId())
                .eventOutcome(courtEvent.getEventOutcome())
                .eventStatus(courtEvent.getEventStatus())
                .hold(typesTransformer.ynToBoolean(courtEvent.getHoldFlag()))
                .judgeName(courtEvent.getJudgeName())
                .nextEventDateTime(typesTransformer.localDateTimeOf(courtEvent.getNextEventDate(), courtEvent.getNextEventStartTime()))
                .nextEventRequest(typesTransformer.ynToBoolean(courtEvent.getNextEventRequestFlag()))
                .offenderProceedingId(courtEvent.getOffenderProceedingId())
                .orderRequested(typesTransformer.ynToBoolean(courtEvent.getOrderRequestedFlag()))
                .outcomeDate(typesTransformer.localDateOf(courtEvent.getOutcomeDate()))
                .outcomeReasonCode(courtEvent.getOutcomeReasonCode())
                .parentEventId(courtEvent.getParentEventId())
                .resultCode(courtEvent.getResultCode())
                .build();
    }

    private List<Charge> courtEventChargesOf(List<CourtEventCharge> charges) {
        return Optional.ofNullable(charges).map(
                courtEventCharges -> courtEventCharges.stream().map(
                        courtEventCharge -> chargeAndSentenceof(courtEventCharge.getOffenderCharge()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }

    private Charge chargeAndSentenceof(OffenderCharge offenderCharge) {
        return Optional.ofNullable(offenderCharge).map(
                oc -> chargesTransformer.chargeOf(oc).toBuilder()
                        .sentences(sentencesOf(oc.getSentences()))
                        .build())
                .orElse(null);
    }

    private List<Sentence> sentencesOf(List<OffenderSentence> sentences) {
        return Optional.ofNullable(sentences).map(
                ss -> ss.stream()
                        .map(s -> sentenceTransformer.sentenceOf(s))
                        .collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
