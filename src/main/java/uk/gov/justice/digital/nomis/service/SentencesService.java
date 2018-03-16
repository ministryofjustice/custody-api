package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Sentence;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderSentencesRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SentencesService {

    private final OffenderSentencesRepository offenderSentencesRepository;
    private final OffenderRepository offenderRepository;

    @Autowired
    public SentencesService(OffenderSentencesRepository offenderSentencesRepository, OffenderRepository offenderRepository) {
        this.offenderSentencesRepository = offenderSentencesRepository;
        this.offenderRepository = offenderRepository;
    }

    @Transactional
    public Page<Sentence> getSentences(Pageable pageable) {

        Page<OffenderSentence> offenderSentences = offenderSentencesRepository.findAll(pageable);

        List<Sentence> sentencesList = offenderSentences.getContent()
                .stream()
                .map(this::sentenceOf)
                .collect(Collectors.toList());

        return new PageImpl<>(sentencesList, pageable, offenderSentences.getTotalElements());
    }

    private Sentence sentenceOf(OffenderSentence offenderSentence) {
        OffenderBooking offenderBooking = offenderSentence.getOffenderBooking();
        return Sentence.builder()
                .bookingId(offenderBooking.getOffenderBookId())
                .offenderId(offenderBooking.getOffenderId())
                .sentenceSequenceNumber(offenderSentence.getSentenceSeq())
                .aggregateAdjustDays(offenderSentence.getAggregateAdjustDays())
                .aggregateTerm(offenderSentence.getAggregateTerm())
                .apdCalculateDate(localDateOf(offenderSentence.getApdCalculatedDate()))
                .ardCalculateDate(localDateOf(offenderSentence.getArdCalculatedDate()))
                .breachLevel(offenderSentence.getBreachLevel())
                .comments(offenderSentence.getCommentText())
                .consecToSentenceSeq(offenderSentence.getConsecToSentenceSeq())
                .counts(offenderSentence.getCounts())
                .crdCalculateDate(localDateOf(offenderSentence.getCrdCalculatedDate()))
                .createdAt(localDateTimeOf(offenderSentence.getCreateDatetime()))
                .dischargeDate(localDateOf(offenderSentence.getDischargeDate()))
                .dprrdCalculatedDate(localDateOf(offenderSentence.getDprrdCalculatedDate()))
                .endDate(localDateOf(offenderSentence.getEndDate()))
                .etdCalculateDate(localDateOf(offenderSentence.getEtdCalculatedDate()))
                .extendedDays(offenderSentence.getExtendedDays())
                .fineAmount(offenderSentence.getFineAmount())
                .hdcedCalculateDate(localDateOf(offenderSentence.getHdcedCalculatedDate()))
                .hdcExclusion(ynToBoolean(offenderSentence.getHdcExclusionFlag()))
                .hdcExclusionReason(offenderSentence.getHdcExclusionReason())
                .ledCalculateDate(localDateOf(offenderSentence.getLedCalculatedDate()))
                .ltdCalculateDate(localDateOf(offenderSentence.getLtdCalculatedDate()))
                .mtdCalculateDate(localDateOf(offenderSentence.getMtdCalculatedDate()))
                .npdCalculateDate(localDateOf(offenderSentence.getNpdCalculatedDate()))
                .pedCalculateDate(localDateOf(offenderSentence.getPedCalculatedDate()))
                .numberOfUnexcusedAbsences(offenderSentence.getNoOfUnexcusedAbsence())
                .prrdCalculateDate(localDateOf(offenderSentence.getPrrdCalculatedDate()))
                .revokedDate(localDateOf(offenderSentence.getRevokedDate()))
                .sedCalculateDate(localDateOf(offenderSentence.getSedCalculatedDate()))
                .sentenceCategory(offenderSentence.getSentenceCategory())
                .sentenceLevel(offenderSentence.getSentenceLevel())
                .sentenceStatus(offenderSentence.getSentenceStatus())
                .sentenceText(offenderSentence.getSentenceText())
                .sled2CalcDate(localDateOf(offenderSentence.getSled2Calc()))
                .startDate(localDateOf(offenderSentence.getStartDate()))
                .startDate2Calc(localDateOf(offenderSentence.getStartDate2Calc()))
                .tariffCalculateDate(localDateOf(offenderSentence.getTariffCalculatedDate()))
                .terminationDate(localDateOf(offenderSentence.getTerminationDate()))
                .terminationReason(offenderSentence.getTerminationReason())
                .tusedCalculatedDate(localDateOf(offenderSentence.getTusedCalculatedDate()))
                .build();
    }

    private LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp).map(t -> t.toLocalDateTime()).orElse(null);
    }

    @Transactional
    public Optional<List<Sentence>> sentencesForOffenderId(Long offenderId) {

        Optional<List<OffenderSentence>> maybeOffenderSentences = Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings().stream()
                        .map(OffenderBooking::getOffenderSentences).
                                flatMap(Collection::stream).
                                collect(Collectors.toList()));

        return maybeOffenderSentences.map(offenderSentences -> offenderSentences.stream().map(this::sentenceOf).collect(Collectors.toList()));
    }

    @Transactional
    public Optional<List<Sentence>> sentencesForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        Optional<List<Sentence>> maybeSentences = maybeOffenderBooking.map(ob -> ob.getOffenderSentences()
                .stream().map(this::sentenceOf).collect(Collectors.toList()));

        return maybeSentences;
    }

    private LocalDate localDateOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp).map(t -> t.toLocalDateTime().toLocalDate()).orElse(null);
    }

    private Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn).map("Y"::equalsIgnoreCase).orElse(null);
    }


}
