package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.XtagEventsRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderEventsTransformer;

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
        return xtagEventsRepository.findAll(oeFilter).stream()
                .map(offenderEventsTransformer::offenderEventOf)
                .collect(Collectors.toList());
    }
}
