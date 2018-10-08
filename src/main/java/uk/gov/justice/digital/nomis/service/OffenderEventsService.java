package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderEvent;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderEventsRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderEventsTransformer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public Optional<List<OffenderEvent>> getEvents(Optional<LocalDateTime> maybeFrom,
                                         Optional<LocalDateTime> maybeTo,
                                         Optional<Set<String>> maybeTypeFilter) {
        LocalDateTime from = maybeFrom.orElse(maybeTo.orElse(LocalDate.now().atStartOfDay()));
        LocalDateTime to = maybeTo.orElse(from.toLocalDate().plusDays(1).atStartOfDay().minusNanos(1));

        Set<String> typeFilter = maybeTypeFilter.map(types -> types.stream().map(String::toUpperCase).collect(Collectors.toSet())).orElse(Collections.EMPTY_SET);

        return getFilteredOffenderEvents(typeFilter, OffenderEventsFilter.builder()
                .from(from)
                .to(to)
                .types(maybeTypeFilter)
                .build());
    }

    public Optional<List<OffenderEvent>> getEventsForOffenderId(Long offenderId,
                                                      Optional<LocalDateTime> maybeFrom,
                                                      Optional<LocalDateTime> maybeTo,
                                                      Optional<Set<String>> maybeTypeFilter) {
        LocalDateTime from = maybeFrom.orElse(maybeTo.orElse(LocalDate.now().atStartOfDay()));
        LocalDateTime to = maybeTo.orElse(from.toLocalDate().plusDays(1).atStartOfDay().minusNanos(1));

        Set<String> typeFilter = maybeTypeFilter.map(types -> types.stream().map(String::toUpperCase).collect(Collectors.toSet())).orElse(Collections.EMPTY_SET);

        return getFilteredOffenderEvents(typeFilter, OffenderEventsFilter.builder()
                .from(from)
                .to(to)
                .types(maybeTypeFilter)
                .offenderId(offenderId)
                .build());
    }

    private Optional<List<OffenderEvent>> getFilteredOffenderEvents(Set<String> typeFilter, OffenderEventsFilter filter) {
        return Optional.ofNullable(offenderEventsRepository.findAll(filter))
                .map(ev -> ev
                        .stream()
                        .sorted(BY_OFFENDER_EVENT_TIMESTAMP)
                        .map(offenderEventsTransformer::offenderEventOf)
                        .filter(oe -> typeFilter.isEmpty() || typeFilter.contains(oe.getEventType()))
                        .collect(Collectors.toList()));
    }
}
