package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderEvent;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.jpa.filters.XtagEventsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderEventsRepository;
import uk.gov.justice.digital.nomis.jpa.repository.XtagEventsRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderEventsTransformer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OffenderEventsService {

    private static final Comparator<uk.gov.justice.digital.nomis.api.OffenderEvent> BY_OFFENDER_EVENT_TIMESTAMP =
            Comparator.comparing(uk.gov.justice.digital.nomis.api.OffenderEvent::getEventDatetime)
                    .reversed();

    private final OffenderEventsTransformer offenderEventsTransformer;
    private final OffenderEventsRepository offenderEventsRepository;
    private final XtagEventsRepository xtagEventsRepository;


    @Autowired
    public OffenderEventsService(OffenderEventsTransformer offenderEventsTransformer,
                                 OffenderEventsRepository offenderEventsRepository,
                                 XtagEventsRepository xtagEventsRepository) {
        this.offenderEventsTransformer = offenderEventsTransformer;
        this.offenderEventsRepository = offenderEventsRepository;
        this.xtagEventsRepository = xtagEventsRepository;
    }

    public Optional<List<OffenderEvent>> getEvents(Optional<LocalDateTime> maybeFrom,
                              Optional<LocalDateTime> maybeTo,
                              Optional<Set<String>> maybeTypeFilter) {
        LocalDateTime from = maybeFrom.orElse(maybeTo.map(to -> to.minusDays(1)).orElse(LocalDate.now().atStartOfDay()));
        LocalDateTime to = maybeTo.orElse(from.plusDays(1));

        Set<String> typeFilter = maybeTypeFilter
                .map(types -> types.stream().map(String::toUpperCase).collect(Collectors.toSet()))
                .orElse(Collections.EMPTY_SET);

        return getFilteredOffenderEvents(typeFilter,
                OffenderEventsFilter.builder().from(from).to(to).types(maybeTypeFilter).build(),
                XtagEventsFilter.builder().from(from).to(to).build());
    }

    public Optional<List<OffenderEvent>> getEventsForOffenderId(Long offenderId,
                                           Optional<LocalDateTime> maybeFrom,
                                           Optional<LocalDateTime> maybeTo,
                                           Optional<Set<String>> maybeTypeFilter) {
        LocalDateTime from = maybeFrom.orElse(maybeTo.map(to -> to.minusDays(1)).orElse(LocalDate.now().atStartOfDay()));
        LocalDateTime to = maybeTo.orElse(from.plusDays(1));

        Set<String> typeFilter = maybeTypeFilter
                .map(types -> types.stream().map(String::toUpperCase).collect(Collectors.toSet()))
                .orElse(Collections.EMPTY_SET);

        return getFilteredOffenderEvents(typeFilter,
                OffenderEventsFilter.builder().from(from).to(to).types(maybeTypeFilter).offenderId(offenderId).build(),
                XtagEventsFilter.builder().from(from).to(to).build());
    }

    private Optional<List<OffenderEvent>> getFilteredOffenderEvents(Set<String> typeFilter, OffenderEventsFilter oeFilter, XtagEventsFilter xtFilter) {
        Optional<Stream<OffenderEvent>> offenderEvents = Optional.ofNullable(offenderEventsRepository.findAll(oeFilter))
                .map(ev -> ev.stream().map(offenderEventsTransformer::offenderEventOf));

        Optional<Stream<OffenderEvent>> xtagEvents = Optional.ofNullable(xtagEventsRepository.findAll(xtFilter))
                .map(ev -> ev.stream().map(offenderEventsTransformer::offenderEventOf));

        return Optional.of(Stream.concat(offenderEvents.orElse(Stream.empty()), xtagEvents.orElse(Stream.empty()))
                .filter(oe -> typeFilter.isEmpty() || typeFilter.contains(oe.getEventType()))
                .sorted(BY_OFFENDER_EVENT_TIMESTAMP)
                .collect(Collectors.toList()));
    }
}
