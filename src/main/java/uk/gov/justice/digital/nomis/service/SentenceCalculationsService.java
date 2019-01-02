package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.SentenceCalculation;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.filters.SentenceCalculationsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderSentenceCalculationsRepository;
import uk.gov.justice.digital.nomis.service.transformer.SentenceCalculationsTransformer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SentenceCalculationsService {

    private static final Comparator<SentenceCalculation> BY_CALCULATION_DATE = Comparator
            .comparing(SentenceCalculation::getSentenceCalculationId)
            .reversed();
    private final SentenceCalculationsTransformer sentenceCalculationsTransformer;
    private final OffenderSentenceCalculationsRepository sentenceCalculationsRepository;
    private final OffenderRepository offenderRepository;

    @Autowired
    public SentenceCalculationsService(SentenceCalculationsTransformer sentenceCalculationsTransformer, OffenderSentenceCalculationsRepository sentenceCalculationsRepository, OffenderRepository offenderRepository) {
        this.sentenceCalculationsTransformer = sentenceCalculationsTransformer;
        this.sentenceCalculationsRepository = sentenceCalculationsRepository;
        this.offenderRepository = offenderRepository;
    }

    public Page<SentenceCalculation> getSentenceCalculations(Pageable pageable, Set<Long> bookingIds) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.OffenderSentCalculation> rawSentenceCalcsPage = sentenceCalculationsRepository.findAll(
                SentenceCalculationsFilter.builder()
                        .bookingIds(bookingIds)
                        .build(),
                pageable);

        List<SentenceCalculation> sentenceCalculations = rawSentenceCalcsPage.getContent()
                .stream()
                .map(sentenceCalculationsTransformer::sentenceCalculationOf)
                .sorted(BY_CALCULATION_DATE)
                .collect(Collectors.toList());

        return new PageImpl<>(sentenceCalculations, pageable, rawSentenceCalcsPage.getTotalElements());
    }

    public Optional<List<SentenceCalculation>> sentenceCalculationsForOffenderId(Long offenderId) {
        Optional<List<uk.gov.justice.digital.nomis.jpa.entity.OffenderSentCalculation>> maybeSentenceCalculations = offenderRepository.findById(offenderId)
                .map(offender ->
                        offender.getOffenderBookings()
                                .stream()
                                .map(OffenderBooking::getOffenderSentCalculations)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList()));

        return maybeSentenceCalculations
                .map(sentCalculations ->
                        sentCalculations
                                .stream()
                                .map(sentenceCalculationsTransformer::sentenceCalculationOf)
                                .sorted(BY_CALCULATION_DATE)
                                .collect(Collectors.toList()));
    }

    public Optional<List<SentenceCalculation>> sentenceCalculationsForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = offenderRepository.findById(offenderId);

        return maybeOffender.flatMap(
                offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst()).
                map(ob -> ob.getOffenderSentCalculations()
                        .stream()
                        .map(sentenceCalculationsTransformer::sentenceCalculationOf)
                        .sorted(BY_CALCULATION_DATE)
                        .collect(Collectors.toList()));
    }

}
