package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.MilitaryRecord;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderMilitaryRecord;

@Component
public class MilitaryRecordsTransformer {
    private final TypesTransformer typesTransformer;

    @Autowired
    public MilitaryRecordsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public MilitaryRecord militaryRecordOf(OffenderMilitaryRecord omr) {
        return MilitaryRecord.builder()
                .bookingId(omr.getId().getOffenderBooking().getOffenderBookId())
                .description(omr.getDescription())
                .dischargeLocation(omr.getDischargeLocation())
                .disciplinaryActionCode(omr.getDisciplinaryActionCode())
                .endDate(typesTransformer.localDateOf(omr.getEndDate()))
                .enlistmentLocation(omr.getEnlistmentLocation())
                .militaryBranchCode(omr.getMilitaryBranchCode())
                .militaryDischargeCode(omr.getMilitaryDischargeCode())
                .militaryRankCode(omr.getMilitaryRankCode())
                .militarySeq(omr.getId().getMilitarySeq())
                .selectiveServicesFlag(omr.getSelectiveServicesFlag())
                .serviceNumber(omr.getServiceNumber())
                .startDate(typesTransformer.localDateOf(omr.getStartDate()))
                .unitNumber(omr.getUnitNumber())
                .warZoneCode(omr.getWarZoneCode())
                .build();
    }
}
