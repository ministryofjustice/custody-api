package uk.gov.justice.digital.nomis.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Case;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.KeyValue;
import uk.gov.justice.digital.nomis.api.Offence;
import uk.gov.justice.digital.nomis.api.Order;
import uk.gov.justice.digital.nomis.api.Result;
import uk.gov.justice.digital.nomis.jpa.entity.HoCode;
import uk.gov.justice.digital.nomis.jpa.entity.OffenceResultCode;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCase;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;
import uk.gov.justice.digital.nomis.jpa.entity.Statute;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderChargesRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OffenderChargesService {

    private final OffenderChargesRepository offenderChargesRepository;
    private final OffenderRepository offenderRepository;

    @Autowired
    public OffenderChargesService(final OffenderChargesRepository offenderChargesRepository,
                                  final OffenderRepository offenderRepository) {
        this.offenderChargesRepository = offenderChargesRepository;
        this.offenderRepository = offenderRepository;
    }

    @Transactional
    public Page<Charge> getCharges(Pageable pageable) {

        Page<OffenderCharge> offenderCharges = offenderChargesRepository.findAll(pageable);

        List<Charge> chargesList = offenderCharges.getContent()
                .stream()
                .map(this::chargeOf)
                .collect(Collectors.toList());

        return new PageImpl<>(chargesList, pageable, offenderCharges.getTotalElements());
    }

    private Charge chargeOf(OffenderCharge oc) {
        return Charge.builder()
                .bookingId(oc.getOffenderBookId())
                .offenderCase(caseOf(oc.getOffenderCase()))
                .chargeId(oc.getOffenderChargeId())
                .chargeSequence(oc.getChargeSeq())
                .chargeStatus(oc.getChargeStatus())
                .cjitOffenceCodes(cjitOffenceCodesMapOf(oc))
                .lidsOffenceNumber(oc.getLidsOffenceNumber())
                .mostSeriousCharge(ynToBoolean(oc.getMostSeriousFlag()))
                .numberOfOffences(oc.getNoOfOffences())
                .offence(offenceOf(oc))
                .offenceDate(Optional.ofNullable(oc.getOffenceDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .offenceRangeDate(Optional.ofNullable(oc.getOffenceRangeDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .order(orderOf(oc))
                .pleaCode(oc.getPleaCode())
                .propertyValue(oc.getPropertyValue())
                .totalPropertyValue(oc.getTotalPropertyValue())
                .result(resultMapOf(oc))
                .build();
    }

    private Map<String, Result> resultMapOf(OffenderCharge oc) {
        ImmutableMap.Builder<String, Result> resultBuilder = ImmutableMap.builder();

        OffenceResultCode offenceResult1 = oc.getOffenceResult1();
        OffenceResultCode offenceResult2 = oc.getOffenceResult2();

        if (offenceResult1 == null && offenceResult2 == null) {
            return null;
        }

        if (offenceResult1 != null) {
            resultBuilder.put("1", Result.builder()
                    .code(offenceResult1.getResultCode())
                    .description(offenceResult1.getDescription())
                    .indicator(oc.getResultCode1Indicator())
                    .build());
        }

        if (offenceResult2 != null) {
            resultBuilder.put("2", Result.builder()
                    .code(offenceResult2.getResultCode())
                    .description(offenceResult2.getDescription())
                    .indicator(oc.getResultCode2Indicator())
                    .build());
        }

        return resultBuilder.build();
    }

    private Order orderOf(OffenderCharge oc) {
        return Optional.ofNullable(oc.getOrder()).map(o ->
                Order.builder()
                        .comments(o.getCommentText())
                        .completeDate(Optional.ofNullable(o.getCompleteDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                        .courtDate(Optional.ofNullable(o.getCourtDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                        .courtEventId(o.getEventId())
                        .courtSeriousnessLevel(o.getCourtSeriousnessLevel())
                        .orderSeriousnessLevel(o.getOrderSeriousnessLevel())
                        .dueDate(Optional.ofNullable(o.getDueDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                        .interventionTierCode(o.getInterventionTierCode())
                        .issueDate(Optional.ofNullable(o.getIssueDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                        .issuingAgencyLocationId(o.getIssuingAgyLocId())
                        .orderId(o.getOrderId())
                        .orderStatus(o.getOrderStatus())
                        .comments(o.getCommentText())
                        .orderType(o.getOrderType())
                        .requestDate(Optional.ofNullable(o.getRequestDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                        .build())
                .orElse(null);
    }

    private Offence offenceOf(OffenderCharge oc) {
        return Optional.ofNullable(oc.getOffence()).map(o ->
                Offence.builder()
                        .active(ynToBoolean(o.getActiveFlag()))
                        .defaultOffenceType(o.getDefaultOffenceType())
                        .description(o.getDescription())
                        .offenceCode(o.getId().getOffenceCode())
                        .oldStatute(statuteOf(o.getOldStatute()))
                        .statute(statuteOf(o.getId().getStatute()))
                        .hoCode(hoCodeOf(o.getHoCode()))
                        .build())
                .orElse(null);
    }

    private KeyValue hoCodeOf(HoCode hoCode) {
        return Optional.ofNullable(hoCode).map(h -> KeyValue.builder()
                .code(h.getHoCode())
                .description(h.getDescription())
                .build())
                .orElse(null);
    }

    private KeyValue statuteOf(Statute statute) {
        return Optional.ofNullable(statute).map(s -> KeyValue.builder()
                .code(s.getStatuteCode())
                .description(s.getDescription()).build())
                .orElse(null);
    }

    private Map<String, String> cjitOffenceCodesMapOf(OffenderCharge oc) {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        String cjitOffenceCode1 = oc.getCjitOffenceCode1();
        String cjitOffenceCode2 = oc.getCjitOffenceCode2();
        String cjitOffenceCode3 = oc.getCjitOffenceCode3();

        if (cjitOffenceCode1 == null && cjitOffenceCode2 == null && cjitOffenceCode3 == null) {
            return null;
        }

        if (cjitOffenceCode1 != null) {
            builder.put("1", cjitOffenceCode1);
        }

        if (cjitOffenceCode2 != null) {
            builder.put("2", cjitOffenceCode2);
        }

        if (cjitOffenceCode3 != null) {
            builder.put("3", cjitOffenceCode3);
        }

        return builder.build();
    }

    private Case caseOf(OffenderCase offenderCase) {
        return Optional.ofNullable(offenderCase).map(oc -> Case.builder()
                .agencyLocationId(oc.getAgyLocId())
                .beginDate(Optional.ofNullable(oc.getBeginDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .caseId(oc.getCaseId())
                .caseInfoNumber(oc.getCaseInfoNumber())
                .caseSequence(oc.getCaseSeq())
                .caseStatus(oc.getCaseStatus())
                .caseType(oc.getCaseType())
                .combinedCaseId(oc.getCombinedCaseId())
                .lidsCaseNumber(oc.getLidsCaseNumber())
                .nomLegalCaseRef(oc.getNomLegalCaseRef())
                .nomLegalCaseRefTransTo(oc.getNomLegalCaserefTransTo())
                .victimLiasonUnit(oc.getVictimLiaisonUnit())
                .build())
                .orElse(null);
    }

    private Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn).map("Y"::equalsIgnoreCase).orElse(null);
    }

    public Optional<List<Charge>> chargesForOffenderId(Long offenderId) {

        Optional<List<OffenderCharge>> maybeOffenderCharges = Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings().stream()
                        .map(OffenderBooking::getOffenderCharges).
                                flatMap(Collection::stream).
                                collect(Collectors.toList()));

        return maybeOffenderCharges.map(offenderCharges -> offenderCharges.stream().map(this::chargeOf).collect(Collectors.toList()));
    }

    public Optional<List<Charge>> chargesForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<List<OffenderCharge>> maybeOffenderCharges = Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings().stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .map(OffenderBooking::getOffenderCharges).
                                flatMap(Collection::stream).
                                collect(Collectors.toList()));

        return maybeOffenderCharges.map(offenderCharges -> offenderCharges.stream().map(this::chargeOf).collect(Collectors.toList()));
    }
}
