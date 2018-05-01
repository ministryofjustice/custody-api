package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.OffenderImprisonmentStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OffenderImprisonStatusTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderImprisonStatusTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public List<OffenderImprisonmentStatus> offenderImprisonStatusesOf(List<uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus> offenderImprisonStatuses) {
        return Optional.ofNullable(offenderImprisonStatuses).map(
                oises -> oises
                        .stream()
                        .filter(ois -> !"N".equalsIgnoreCase(ois.getLatestStatus()))
                        .map(this::offenderImprisonStatusOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public OffenderImprisonmentStatus offenderImprisonStatusOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus ois) {
        return OffenderImprisonmentStatus.builder()
                .agyLocId(ois.getAgyLocId())
                .commentText(ois.getCommentText())
                .effectiveDateTime(typesTransformer.localDateTimeOf(ois.getEffectiveDate(), ois.getEffectiveTime()))
                .expiryDate(typesTransformer.localDateOf(ois.getExpiryDate()))
                .imprisonmentStatus(ois.getImprisonmentStatus())
                .imprisonStatusSeq(ois.getImprisonStatusSeq())
                .latestStatus(typesTransformer.ynToBoolean(ois.getLatestStatus()))
                .offenderBookId(ois.getOffenderBookId())
                .build();
    }
}