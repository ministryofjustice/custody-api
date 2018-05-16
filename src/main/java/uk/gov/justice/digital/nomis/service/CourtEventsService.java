package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.CourtEvent;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.CourtEventsTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourtEventsService {
    private final CourtEventsTransformer courtEventsTransformer;
    private final OffenderRepository offenderRepository;


    @Autowired
    public CourtEventsService(CourtEventsTransformer courtEventsTransformer, OffenderRepository offenderRepository) {
        this.courtEventsTransformer = courtEventsTransformer;
        this.offenderRepository = offenderRepository;
    }


    public Optional<List<CourtEvent>> courtEventsForOffenderId(Long offenderId) {

        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender ->
                        offender.getOffenderBookings()
                                .stream()
                                .flatMap(offenderBooking -> offenderBooking.getCourtEvents().stream())
                                .sorted(byCourtEventDate())
                                .map(courtEventsTransformer::courtEventOf)
                                .collect(Collectors.toList()));

    }

    private Comparator<uk.gov.justice.digital.nomis.jpa.entity.CourtEvent> byCourtEventDate() {
        return Comparator.comparing(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent::getEventDate).reversed()
                .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent::getStartTime).reversed()
                .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent::getEventId).reversed();
    }
}
