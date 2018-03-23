package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderExternalMovement;
import uk.gov.justice.digital.nomis.jpa.filters.MovementsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.ExternalMovementsRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.MovementsTransformer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementsService {

    private final ExternalMovementsRepository externalMovementsRepository;
    private final OffenderRepository offenderRepository;
    private final MovementsTransformer movementsTransformer;

    @Autowired
    public MovementsService(ExternalMovementsRepository externalMovementsRepository, OffenderRepository offenderRepository, MovementsTransformer movementsTransformer) {
        this.externalMovementsRepository = externalMovementsRepository;
        this.offenderRepository = offenderRepository;
        this.movementsTransformer = movementsTransformer;
    }

    @Transactional
    public Page<ExternalMovement> getMovements(Pageable pageable, MovementsFilter movementsFilter) {
        Page<OffenderExternalMovement> externalMovements = externalMovementsRepository.findAll(movementsFilter, pageable);

        List<ExternalMovement> movementList = externalMovements.getContent().stream().map(
                movementsTransformer::movementOf).collect(Collectors.toList());

        return new PageImpl<>(movementList, pageable, externalMovements.getTotalElements());
    }

    @Transactional
    public Optional<List<ExternalMovement>> getOffenderMovements(Long offenderId) {
        Optional<List<uk.gov.justice.digital.nomis.jpa.entity.OffenderExternalMovement>> maybeOffenderMovements =
                Optional.ofNullable(offenderRepository.findOne(offenderId))
                        .map(offender -> offender.getOffenderBookings().stream()
                                .map(OffenderBooking::getOffenderExternalMovements).
                                        flatMap(Collection::stream).
                                        collect(Collectors.toList()));

        return maybeOffenderMovements.map(externalMovements -> externalMovements
                .stream()
                .map(movementsTransformer::movementOf)
                .collect(Collectors.toList()));
    }


}
