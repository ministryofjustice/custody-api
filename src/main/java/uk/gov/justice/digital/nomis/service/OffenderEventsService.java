package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderEvent;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderEventsRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderEventsTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OffenderEventsService {

    private static final Comparator<uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent> BY_OFFENDER_EVENT_TIMESTAMP =
            Comparator.comparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent::getEventTimestamp)
                    .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent::getEventId)
                    .reversed();

    private final OffenderEventsTransformer offenderEventsTransformer;
    private final OffenderEventsRepository offenderEventsRepository;
    private final OffenderRepository offenderRepository;


    @Autowired
    public OffenderEventsService(OffenderEventsTransformer offenderEventsTransformer, OffenderEventsRepository offenderEventsRepository, OffenderRepository offenderRepository) {
        this.offenderEventsTransformer = offenderEventsTransformer;
        this.offenderEventsRepository = offenderEventsRepository;
        this.offenderRepository = offenderRepository;
    }

    public Page<OffenderEvent> getEvents(Pageable pageable, OffenderEventsFilter offenderEventsFilter) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent> rawOffenderEventsPage = offenderEventsRepository.findAll(offenderEventsFilter, pageable);

        List<OffenderEvent> events = rawOffenderEventsPage.getContent()
                .stream()
                .sorted(BY_OFFENDER_EVENT_TIMESTAMP)
                .map(offenderEventsTransformer::offenderEventOf)
                .collect(Collectors.toList());

        return new PageImpl<>(events, pageable, rawOffenderEventsPage.getTotalElements());
    }

    public Optional<List<OffenderEvent>> eventsForOffenderId(Long offenderId) {

        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderEvents()
                        .stream()
                        .sorted(BY_OFFENDER_EVENT_TIMESTAMP)
                        .map(offenderEventsTransformer::offenderEventOf)
                        .collect(Collectors.toList()));

    }

}
