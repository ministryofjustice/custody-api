package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.XtagEventsRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderEventsTransformer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XtagEventsService {

    private final XtagEventsRepository xtagEventsRepository;
    private final OffenderEventsTransformer offenderEventsTransformer;

    @Autowired
    public XtagEventsService(XtagEventsRepository xtagEventsRepository, OffenderEventsTransformer offenderEventsTransformer) {
        this.xtagEventsRepository = xtagEventsRepository;
        this.offenderEventsTransformer = offenderEventsTransformer;
    }

    public List<uk.gov.justice.digital.nomis.api.OffenderEvent> findAll(OffenderEventsFilter oeFilter) {
        return xtagEventsRepository.findAll(fudgedXtagFilterOf(oeFilter)).stream()
                .map(offenderEventsTransformer::offenderEventOf)
                .collect(Collectors.toList());
    }

    private OffenderEventsFilter fudgedXtagFilterOf(OffenderEventsFilter oeFilter) {
        // Xtag events are in UTC all year round at rest in Oracle.
        // So we have to compensate when filtering by date. The Nomis data set
        // is stored at rest as Europe/London and so is affected by daylight savings.
        final Instant now = Instant.now();

        return oeFilter.toBuilder()
                .from(asUtc(oeFilter.getFrom(), now))
                .to(asUtc(oeFilter.getTo(), now))
                .build();
    }

    public static LocalDateTime asUtc(LocalDateTime localDateTime, Instant now) {
        if (ZoneId.of("Europe/London").getRules().isDaylightSavings(now)) {
            return localDateTime.minusHours(1);
        }
        return localDateTime;

    }
}
