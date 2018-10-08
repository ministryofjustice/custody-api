package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.KeyValue;
import uk.gov.justice.digital.nomis.api.MilitaryRecord;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderMilitaryRecord;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCodePK;
import uk.gov.justice.digital.nomis.jpa.repository.ReferenceCodesRepository;

import java.util.Optional;

@Component
public class MilitaryRecordsTransformer {
    private static final String MLTY_BRANCH = "MLTY_BRANCH";
    private static final String MLTY_DISCP = "MLTY_DISCP";
    private static final String MLTY_DSCHRG = "MLTY_DSCHRG";
    private static final String MLTY_RANK = "MLTY_RANK";
    private static final String MLTY_WZONE = "MLTY_WZONE";

    private final TypesTransformer typesTransformer;
    private final ReferenceCodesRepository referenceCodesRepository;

    @Autowired
    public MilitaryRecordsTransformer(TypesTransformer typesTransformer, ReferenceCodesRepository referenceCodesRepository) {
        this.typesTransformer = typesTransformer;
        this.referenceCodesRepository = referenceCodesRepository;
    }

    public MilitaryRecord militaryRecordOf(OffenderMilitaryRecord omr) {
        return MilitaryRecord.builder()
                .bookingId(omr.getId().getOffenderBooking().getOffenderBookId())
                .description(omr.getDescription())
                .dischargeLocation(omr.getDischargeLocation())
                .disciplinaryActionCode(getReferenceDataOf(omr.getDisciplinaryActionCode(), MLTY_DISCP))
                .endDate(typesTransformer.localDateOf(omr.getEndDate()))
                .enlistmentLocation(omr.getEnlistmentLocation())
                .militaryBranchCode(getReferenceDataOf(omr.getMilitaryBranchCode(), MLTY_BRANCH))
                .militaryDischargeCode(getReferenceDataOf(omr.getMilitaryDischargeCode(), MLTY_DSCHRG))
                .militaryRankCode(getReferenceDataOf(omr.getMilitaryRankCode(), MLTY_RANK))
                .militarySeq(omr.getId().getMilitarySeq())
                .selectiveServices(typesTransformer.ynToBoolean(omr.getSelectiveServicesFlag()))
                .serviceNumber(omr.getServiceNumber())
                .startDate(typesTransformer.localDateOf(omr.getStartDate()))
                .unitNumber(omr.getUnitNumber())
                .warZoneCode(getReferenceDataOf(omr.getWarZoneCode(), MLTY_WZONE))
                .build();
    }

    private KeyValue getReferenceDataOf(String code, String domain) {
        return Optional.ofNullable(code != null ?
                referenceCodesRepository.findOne(ReferenceCodePK.builder()
                        .code(code)
                        .domain(domain)
                        .build()) : null)
                .map(rc -> KeyValue.builder().code(rc.getCode()).description(rc.getDescription()).build())
                .orElse(null);
    }
}
