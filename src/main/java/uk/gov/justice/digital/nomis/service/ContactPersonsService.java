package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderContactPerson;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.ContactPersonsTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactPersonsService {

    private final ContactPersonsTransformer contactPersonsTransformer;
    private final OffenderRepository offenderRepository;

    @Autowired
    public ContactPersonsService(ContactPersonsTransformer contactPersonsTransformer, OffenderRepository offenderRepository) {
        this.contactPersonsTransformer = contactPersonsTransformer;
        this.offenderRepository = offenderRepository;
    }


    public Optional<List<OffenderContactPerson>> contactPersonsForOffender(Long offenderId) {

        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings()
                        .stream()
                        .flatMap(offenderBooking -> offenderBooking.getOffenderContactPersons().stream())
                        .map(contactPersonsTransformer::offenderContactPersonOf)
                        .collect(Collectors.toList()));

    }
}
