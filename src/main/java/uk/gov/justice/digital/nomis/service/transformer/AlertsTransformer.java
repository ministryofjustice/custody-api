package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Alert;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAlert;

import java.util.Optional;

@Component
public class AlertsTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public AlertsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public Alert alertOf(OffenderAlert offenderAlert) {
        return Optional.ofNullable(offenderAlert).map(
                alert -> Alert.builder()
                        .alertCode(alert.getAlertCode())
                        .alertDate(typesTransformer.localDateOf(alert.getAlertDate()))
                        .alertSeq(alert.getAlertSeq())
                        .alertStatus(alert.getAlertStatus())
                        .alertType(alert.getAlertType())
                        .authorizePersonText(alert.getAuthorizePersonText())
                        .bookingId(alert.getOffenderBookId())
                        .caseloadId(alert.getCaseloadId())
                        .caseloadType(alert.getCaseloadType())
                        .comments(alert.getCommentText())
                        .expiryDate(typesTransformer.localDateOf(alert.getExpiryDate()))
                        .rootOffenderId(alert.getRootOffenderId())
                        .verified(typesTransformer.ynToBoolean(alert.getVerifiedFlag()))
                        .build()).orElse(null);
    }
}
