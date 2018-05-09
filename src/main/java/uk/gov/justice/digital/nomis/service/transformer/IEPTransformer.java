package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.IepLevel;
import uk.gov.justice.digital.nomis.api.OffenderIepLevel;

import java.util.Optional;

@Component
public class IEPTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public IEPTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public OffenderIepLevel offenderIepLevelOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderIepLevel offenderIepLevel) {
        return Optional.ofNullable(offenderIepLevel)
                .map(iep -> OffenderIepLevel.builder()
                        .bookingId(iep.getOffenderBookId())
                        .comments(iep.getCommentText())
                        .iepDateTime(typesTransformer.localDateTimeOf(iep.getIepDate(), iep.getIepTime()))
                        .iepLevel(iepLevelOf(iep.getIepLevelThing()))
                        .iepLevelSeq(iep.getIepLevelSeq())
                        .build())
                .orElse(null);
    }

    private IepLevel iepLevelOf(uk.gov.justice.digital.nomis.jpa.entity.IepLevel iep) {
        return Optional.ofNullable(iep)
                .map(iepLevel -> IepLevel.builder()
                        .active(typesTransformer.ynToBoolean(iep.getActiveFlag()))
                        .agyLocId(iep.getAgyLocId())
                        .convictedSpendLimit(iep.getConvictedSpendLimit())
                        .convictedTransferLimit(iep.getConvictedTransferLimit())
                        .expiryDate(typesTransformer.localDateTimeOf(iep.getExpiryDate()))
                        .iepLevel(iep.getIepLevel())
                        .isDefault(typesTransformer.ynToBoolean(iep.getDefaultFlag()))
                        .remandSpendLimit(iep.getRemandSpendLimit())
                        .remandTransferLimit(iep.getRemandTransferLimit())
                        .build())
                .orElse(null);
    }

}
