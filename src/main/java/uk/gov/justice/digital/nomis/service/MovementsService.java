package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderExternalMovement;
import uk.gov.justice.digital.nomis.jpa.filters.MovementsFilter;
import uk.gov.justice.digital.nomis.jpa.repository.ExternalMovementsRepository;
import uk.gov.justice.digital.nomis.service.transformer.MovementsTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementsService {

    private final ExternalMovementsRepository externalMovementsRepository;
    private final MovementsTransformer movementsTransformer;

    @Autowired
    public MovementsService(ExternalMovementsRepository externalMovementsRepository, MovementsTransformer movementsTransformer) {
        this.externalMovementsRepository = externalMovementsRepository;
        this.movementsTransformer = movementsTransformer;
    }

    @Transactional
    public Page<ExternalMovement> getMovements(Pageable pageable, MovementsFilter movementsFilter) {
        Page<OffenderExternalMovement> externalMovements = externalMovementsRepository.findAll(movementsFilter, pageable);

        List<ExternalMovement> movementList = externalMovements.getContent().stream().map(
                movementsTransformer::movementOf).collect(Collectors.toList());

        return new PageImpl<>(movementList, pageable, externalMovements.getTotalElements());
    }

}
