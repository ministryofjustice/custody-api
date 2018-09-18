package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.CourtEvent;
import uk.gov.justice.digital.nomis.api.OutcomeReason;
import uk.gov.justice.digital.nomis.api.Sentence;
import uk.gov.justice.digital.nomis.jpa.entity.CourtEventCharge;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;
import uk.gov.justice.digital.nomis.jpa.repository.OffenceResultsCodeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourtEventsTransformer {

    private final TypesTransformer typesTransformer;
    private final ChargesTransformer chargesTransformer;
    private final SentenceTransformer sentenceTransformer;
    private final ReferenceDataTranformer referenceDataTranformer;
    private final OffenceResultsCodeRepository offenceResultsCodeRepository;

    @Autowired
    public CourtEventsTransformer(TypesTransformer typesTransformer, ChargesTransformer chargesTransformer, SentenceTransformer sentenceTransformer, ReferenceDataTranformer referenceDataTranformer, OffenceResultsCodeRepository offenceResultsCodeRepository) {
        this.typesTransformer = typesTransformer;
        this.chargesTransformer = chargesTransformer;
        this.sentenceTransformer = sentenceTransformer;
        this.referenceDataTranformer = referenceDataTranformer;
        this.offenceResultsCodeRepository = offenceResultsCodeRepository;
    }

    public CourtEvent courtEventOf(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent courtEvent) {
        return CourtEvent.builder()
                .agencyLocation(referenceDataTranformer.agencyLocationOf(courtEvent.getAgencyLocation()))
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
                .outcomeReason(outcomeReasonOf(courtEvent))
                .parentEventId(courtEvent.getParentEventId())
                .resultCode(courtEvent.getResultCode())
                .build();
    }

    private OutcomeReason outcomeReasonOf(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent ce) {
        return Optional.ofNullable(ce.getOutcomeReasonCode() != null ?
            offenceResultsCodeRepository.findOne(ce.getOutcomeReasonCode()): null)
                .map(orc -> OutcomeReason.builder()
                        .Code(orc.getResultCode())
                        .Description(orc.getDescription())
                        .Active(typesTransformer.isActiveOf(orc.getActiveFlag()))
                        .ChargeStatus(typesTransformer.isActiveOf(orc.getChargeStatus()))
                        .DispositionCode(orc.getDispositionCode())
                        .ExpiryDate(typesTransformer.localDateTimeOf(orc.getExpiryDate()))
                        .Conviction(typesTransformer.ynToBoolean(orc.getConvictionFlag()))
                        .build())
                .orElse(null);
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
                        .map(sentenceTransformer::sentenceOf)
                        .collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
