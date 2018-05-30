package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderContactPerson;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.ContactPersonsTransformer;

import java.util.Comparator;
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
                        .sorted(byLastModified())
                        .map(contactPersonsTransformer::offenderContactPersonOf)
                        .collect(Collectors.toList()));

    }

    private Comparator<uk.gov.justice.digital.nomis.jpa.entity.OffenderContactPerson> byLastModified() {
        return Comparator.comparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderContactPerson::getCreateDatetime)
                .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderContactPerson::getModifyDatetime)
                .reversed();
    }


}
