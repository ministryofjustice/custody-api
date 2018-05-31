package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.ImprisonmentStatus;
import uk.gov.justice.digital.nomis.api.OffenderImprisonmentStatus;
import uk.gov.justice.digital.nomis.jpa.repository.ImprisonmentStatusesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ImprisonStatusTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public ImprisonStatusTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public List<OffenderImprisonmentStatus> offenderImprisonStatusesOf(List<uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus> offenderImprisonStatuses) {
        return Optional.ofNullable(offenderImprisonStatuses)
                .map(oises -> oises
                        .stream()
                        .filter(ois -> !"N".equalsIgnoreCase(ois.getLatestStatus()))
                        .map(this::offenderImprisonStatusOf)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public OffenderImprisonmentStatus offenderImprisonStatusOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus ois) {
        return OffenderImprisonmentStatus.builder()
                .agencyLocationId(ois.getAgyLocId())
                .commentText(ois.getCommentText())
                .effectiveDateTime(typesTransformer.localDateTimeOf(ois.getEffectiveDate(), ois.getEffectiveTime()))
                .expiryDate(typesTransformer.localDateOf(ois.getExpiryDate()))
                .imprisonmentStatusCode(ois.getImprisonmentStatus())
                .imprisonStatusSeq(ois.getImprisonStatusSeq())
                .latestStatus(typesTransformer.ynToBoolean(ois.getLatestStatus()))
                .bookingId(ois.getOffenderBookId())
                .build();
    }
}