package uk.gov.justice.digital.nomis.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Case;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.Order;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCase;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;
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
                .bookingId(oc.getOffenderBooking().getOffenderBookId())
                .offenderId(oc.getOffenderBooking().getRootOffenderId())
                .offenderCase(caseOf(oc.getOffenderCase()))
                .chargeId(oc.getOffenderChargeId())
                .chargeSequence(oc.getChargeSeq())
                .chargeStatus(oc.getChargeStatus())
                .cjitOffenceCodes(cjitOffenceCodesMapOf(oc))
                .lidsOffenceNumber(oc.getLidsOffenceNumber())
                .mostSeriousCharge(ynToBoolean(oc.getMostSeriousFlag()))
                .numberOfOffences(oc.getNoOfOffences())
                .offenceCode(oc.getOffenceCode())
                .statuteCode(oc.getStatuteCode())
                .offenceDate(Optional.ofNullable(oc.getOffenceDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .offenceRangeDate(Optional.ofNullable(oc.getOffenceRangeDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .order(orderOf(oc))
                .pleaCode(oc.getPleaCode())
                .propertyValue(oc.getPropertyValue())
                .totalPropertyValue(oc.getTotalPropertyValue())
                .resultCodes(resultCodesOf(oc))
                .build();
    }

    private List<String> resultCodesOf(OffenderCharge oc) {
        String resultCode1 = oc.getResultCode1();
        String resultCode2 = oc.getResultCode2();

        if (resultCode1 == null && resultCode2 == null) {
            return null;
        }

        ImmutableList.Builder<String> builder = ImmutableList.builder();

        if (resultCode1 != null) {
            builder.add(resultCode1);
        }

        if (resultCode2 != null) {
            builder.add(resultCode2);
        }

        return builder.build();
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
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        Optional<List<Charge>> maybeCharges = maybeOffenderBooking.map(ob -> ob.getOffenderCharges()
                .stream().map(this::chargeOf).collect(Collectors.toList()));

        return maybeCharges;
    }
}
