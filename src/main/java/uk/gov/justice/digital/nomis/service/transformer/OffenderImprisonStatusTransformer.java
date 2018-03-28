package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.OffenderImprisonStatus;

import java.sql.Time;
import java.util.Comparator;
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

    public List<OffenderImprisonStatus> offenderImprisonStatusesOf(List<uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus> offenderImprisonStatuses) {
        return Optional.ofNullable(offenderImprisonStatuses).map(
                oises -> oises
                        .stream()
                        .filter(ois -> !"N".equalsIgnoreCase(ois.getLatestStatus()))
                        .sorted(Comparator
                                .comparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus::getEffectiveDate)
                                .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus::getEffectiveTime)
                                .reversed())
                        .map(this::offenderImprisonStatusOf
                        ).collect(Collectors.toList())).orElse(null);
    }

    public OffenderImprisonStatus offenderImprisonStatusOf(uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus ois) {
        return OffenderImprisonStatus.builder()
                .agyLocId(ois.getAgyLocId())
                .commentText(ois.getCommentText())
                .effectiveDate(typesTransformer.localDateTimeOf(ois.getEffectiveDate()))
                .effectiveTime(Optional.ofNullable(ois.getEffectiveTime()).map(Time::toLocalTime).orElse(null))
                .expiryDate(typesTransformer.localDateOf(ois.getExpiryDate()))
                .imprisonmentStatus(ois.getImprisonmentStatus())
                .imprisonStatusSeq(ois.getImprisonStatusSeq())
                .latestStatus(typesTransformer.ynToBoolean(ois.getLatestStatus()))
                .offenderBookId(ois.getOffenderBookId())
                .build();
    }
}