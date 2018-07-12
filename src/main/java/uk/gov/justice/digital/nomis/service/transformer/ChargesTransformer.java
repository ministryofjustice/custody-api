package uk.gov.justice.digital.nomis.service.transformer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Case;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.OffenceResult;
import uk.gov.justice.digital.nomis.api.Order;
import uk.gov.justice.digital.nomis.api.OutcomeReason;
import uk.gov.justice.digital.nomis.jpa.entity.Offence;
import uk.gov.justice.digital.nomis.jpa.entity.OffenceIndicator;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCase;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;
import uk.gov.justice.digital.nomis.jpa.repository.OffenceResultsCodeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChargesTransformer {

    private final TypesTransformer typesTransformer;
    private OffenceResultsCodeRepository offenceResultsCodeRepository;

    @Autowired
    public ChargesTransformer(TypesTransformer typesTransformer, OffenceResultsCodeRepository offenceResultsCodeRepository) {
        this.typesTransformer = typesTransformer;
        this.offenceResultsCodeRepository = offenceResultsCodeRepository;
    }

    public Charge chargeOf(OffenderCharge oc) {
        return Charge.builder()
                .bookingId(oc.getOffenderBooking().getOffenderBookId())
                .offenderId(oc.getOffenderBooking().getRootOffenderId())
                .offenderCase(caseOf(oc.getOffenderCase()))
                .chargeId(oc.getOffenderChargeId())
                .chargeSequence(oc.getChargeSeq())
                .chargeStatus(oc.getChargeStatus())
                .cjitOffenceCodes(cjitOffenceCodesMapOf(oc))
                .lidsOffenceNumber(oc.getLidsOffenceNumber())
                .mostSeriousCharge(typesTransformer.ynToBoolean(oc.getMostSeriousFlag()))
                .numberOfOffences(oc.getNoOfOffences())
                .offenceCode(oc.getOffenceCode())
                .statuteCode(oc.getStatuteCode())
                .offenceSeverityRanking(Optional.ofNullable(oc.getOffence()).map(Offence::getSeverityRanking).orElse(null))
                .offenceDate(Optional.ofNullable(oc.getOffenceDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .offenceRangeDate(Optional.ofNullable(oc.getOffenceRangeDate()).map(d -> d.toLocalDateTime().toLocalDate()).orElse(null))
                .order(orderOf(oc))
                .pleaCode(oc.getPleaCode())
                .propertyValue(oc.getPropertyValue())
                .totalPropertyValue(oc.getTotalPropertyValue())
                .resultCodes(resultCodesOf(oc))
                .offenceIndicatorCodes(offenceIndicatorCodesOf(oc))
                .build();
    }

    private List<String> offenceIndicatorCodesOf(OffenderCharge oc) {
        return Optional.ofNullable(oc.getOffence())
                .map(o ->o.getOffenceIndicators()
                        .stream()
                        .map(OffenceIndicator::getIndicatorCode)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public List<OffenceResult> resultCodesOf(OffenderCharge oc) {
        String resultCode1 = oc.getResultCode1();
        String resultCode2 = oc.getResultCode2();

        if (resultCode1 == null && resultCode2 == null) {
            return null;
        }

        ImmutableList.Builder<OffenceResult> builder = ImmutableList.builder();

        if (resultCode1 != null) {
            builder.add(offenceResultOf(resultCode1));
        }

        if (resultCode2 != null) {
            builder.add(offenceResultOf(resultCode2));
        }

        return builder.build();
    }

    private OffenceResult offenceResultOf(String rc) {
        return Optional.ofNullable(rc != null ?
                offenceResultsCodeRepository.findOne(rc): null)
                .map(orc -> OffenceResult.builder()
                        .Code(orc.getResultCode())
                        .Description(orc.getDescription())
                        .Active(typesTransformer.isActiveOf(orc.getActiveFlag()))
                        .ChargeStatus(typesTransformer.isActiveOf(orc.getChargeStatus()))
                        .DispositionCode(orc.getDispositionCode())
                        .ExpiryDate(typesTransformer.localDateTimeOf(orc.getExpiryDate()))
                        .Conviction(typesTransformer.ynToBoolean(orc.getConvictionFlag()))
                        .build())
                .orElse(null);
    }

    public Order orderOf(OffenderCharge oc) {
        return Optional.ofNullable(oc.getOrder())
                .map(o -> Order.builder()
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

    public Map<String, String> cjitOffenceCodesMapOf(OffenderCharge oc) {
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

    public Case caseOf(OffenderCase offenderCase) {
        return Optional.ofNullable(offenderCase)
                .map(oc -> Case.builder()
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
}