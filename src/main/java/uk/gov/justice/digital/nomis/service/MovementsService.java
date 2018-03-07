package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.jpa.entity.MovementReason;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderExternalMovement;
import uk.gov.justice.digital.nomis.jpa.repository.ExternalMovementsRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementsService {

    private final ExternalMovementsRepository externalMovementsRepository;

    @Autowired
    public MovementsService(ExternalMovementsRepository externalMovementsRepository) {
        this.externalMovementsRepository = externalMovementsRepository;
    }

    @Transactional
    public Page<ExternalMovement> getMovements(Pageable pageable) {
        Page<OffenderExternalMovement> externalMovements = externalMovementsRepository.findAllByOrderByMovementTimeDescIdMovementSeqDesc(pageable);

        List<ExternalMovement> movementList = externalMovements.getContent().stream().map(
                em -> {
                    MovementReason movementReason = em.getMovementReason();

                    return ExternalMovement.builder()
                            .active(em.getActiveFlag())
                            .comments(em.getCommentText())
                            .fromAddressId(em.getFromAddressId())
                            .toAddressId(em.getToAddressId())
                            .fromAgencyLocationId(em.getFromAgyLocId())
                            .toAgencyCodeLocationId(em.getToAgyLocId())
                            .movementDirection(em.getDirectionCode())
                            .movementReasonCode(movementReason.getMovementReasonCode())
                            .movementTypeCode(movementReason.getMovementType())
                            .offenderBookingId(em.getId().getOffenderBooking().getOffenderBookId())
                            .offenderId(em.getId().getOffenderBooking().getRootOffenderId())
                            .sequenceNumber(em.getId().getMovementSeq())
                            .movementDateTime(em.getMovementTime().toLocalDateTime())
                            .unemploymentPay(ynToBoolean(movementReason.getUnemploymentPay()))
                            .escapeRecapture(ynToBoolean(movementReason.getEscRecapFlag()))
                            .inMovementType(movementReason.getInMovementType())
                            .inMovementReasonCode(movementReason.getMovementReasonCode())
                            .transportation(ynToBoolean(movementReason.getTransportationFlag()))
                            .internalScheduleType(em.getInternalScheduleType())
                            .internalScheduleReasonCode(em.getInternalScheduleReasonCode())
                            .arrestAgencyLocationId(em.getArrestAgencyLocId())
                            .toProvStatCode(em.getToProvStatCode())
                            .escortCode(em.getEscortCode())
                            .escortText(em.getEscortText())
                            .reportingDateTime(Optional.ofNullable(em.getReportingTime()).map(Timestamp::toLocalDateTime).orElse(null))
                            .toCityCode(em.getToCity())
                            .fromCityCode(em.getFromCity())
                            .toCountryCode(em.getToCountryCode())
                            .ojLocationCode(em.getOjLocationCode())
                            .build();
                }).collect(Collectors.toList());

        return new PageImpl<>(movementList, pageable, externalMovements.getTotalElements());
    }

    private Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn).map("Y"::equalsIgnoreCase).orElse(null);
    }
}
