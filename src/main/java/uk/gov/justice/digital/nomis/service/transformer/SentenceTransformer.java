package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Sentence;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;

@Component
public class SentenceTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public SentenceTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public Sentence sentenceOf(OffenderSentence offenderSentence) {
        OffenderBooking offenderBooking = offenderSentence.getOffenderBooking();
        return Sentence.builder()
                .bookingId(offenderBooking.getOffenderBookId())
                .offenderId(offenderBooking.getOffenderId())
                .sentenceSequenceNumber(offenderSentence.getSentenceSeq())
                .aggregateAdjustDays(offenderSentence.getAggregateAdjustDays())
                .aggregateTerm(offenderSentence.getAggregateTerm())
                .apdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getApdCalculatedDate()))
                .ardCalculatedDate(typesTransformer.localDateOf(offenderSentence.getArdCalculatedDate()))
                .breachLevel(offenderSentence.getBreachLevel())
                .comments(offenderSentence.getCommentText())
                .consecToSentenceSeq(offenderSentence.getConsecToSentenceSeq())
                .counts(offenderSentence.getCounts())
                .crdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getCrdCalculatedDate()))
                .createdAt(typesTransformer.localDateTimeOf(offenderSentence.getCreateDatetime()))
                .dischargeDate(typesTransformer.localDateOf(offenderSentence.getDischargeDate()))
                .dprrdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getDprrdCalculatedDate()))
                .endDate(typesTransformer.localDateOf(offenderSentence.getEndDate()))
                .etdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getEtdCalculatedDate()))
                .extendedDays(offenderSentence.getExtendedDays())
                .fineAmount(offenderSentence.getFineAmount())
                .hdcedCalculatedDate(typesTransformer.localDateOf(offenderSentence.getHdcedCalculatedDate()))
                .hdcExclusion(typesTransformer.ynToBoolean(offenderSentence.getHdcExclusionFlag()))
                .hdcExclusionReason(offenderSentence.getHdcExclusionReason())
                .ledCalculatedDate(typesTransformer.localDateOf(offenderSentence.getLedCalculatedDate()))
                .ltdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getLtdCalculatedDate()))
                .mtdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getMtdCalculatedDate()))
                .npdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getNpdCalculatedDate()))
                .pedCalculatedDate(typesTransformer.localDateOf(offenderSentence.getPedCalculatedDate()))
                .numberOfUnexcusedAbsences(offenderSentence.getNoOfUnexcusedAbsence())
                .prrdCalculatedDate(typesTransformer.localDateOf(offenderSentence.getPrrdCalculatedDate()))
                .revokedDate(typesTransformer.localDateOf(offenderSentence.getRevokedDate()))
                .sedCalculatedDate(typesTransformer.localDateOf(offenderSentence.getSedCalculatedDate()))
                .sentenceCategory(offenderSentence.getSentenceCategory())
                .sentenceLevel(offenderSentence.getSentenceLevel())
                .sentenceStatus(offenderSentence.getSentenceStatus())
                .sentenceText(offenderSentence.getSentenceText())
                .sled2CalcDate(typesTransformer.localDateOf(offenderSentence.getSled2Calc()))
                .startDate(typesTransformer.localDateOf(offenderSentence.getStartDate()))
                .startDate2Calc(typesTransformer.localDateOf(offenderSentence.getStartDate2Calc()))
                .tariffCalculatedDate(typesTransformer.localDateOf(offenderSentence.getTariffCalculatedDate()))
                .terminationDate(typesTransformer.localDateOf(offenderSentence.getTerminationDate()))
                .terminationReason(offenderSentence.getTerminationReason())
                .tusedCalculatedDate(typesTransformer.localDateOf(offenderSentence.getTusedCalculatedDate()))
                .build();
    }
}