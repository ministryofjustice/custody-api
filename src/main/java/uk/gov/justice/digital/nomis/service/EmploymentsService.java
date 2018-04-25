package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.Employment;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.EmploymentsTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmploymentsService {

    private final EmploymentsTransformer employmentsTransformer;
    private final OffenderRepository offenderRepository;

    @Autowired
    public EmploymentsService(EmploymentsTransformer employmentsTransformer, OffenderRepository offenderRepository) {
        this.employmentsTransformer = employmentsTransformer;
        this.offenderRepository = offenderRepository;
    }

    public Optional<List<Employment>> employmentsForOffenderId(Long offenderId) {

        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender ->
                        offender.getOffenderBookings()
                                .stream()
                                .flatMap(offenderBooking -> offenderBooking.getOffenderEmployments().stream())
                                .map(employmentsTransformer::employmentOf)
                                .collect(Collectors.toList()));

    }
}
