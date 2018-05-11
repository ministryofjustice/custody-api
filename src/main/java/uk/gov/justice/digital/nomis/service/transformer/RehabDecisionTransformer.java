package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.RehabDecision;
import uk.gov.justice.digital.nomis.api.RehabProvider;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderRehabDecision;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderRehabProvider;

import java.util.Optional;

@Component
public class RehabDecisionTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public RehabDecisionTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public RehabDecision rehabDecisionOf(OffenderRehabDecision offenderRehabDecision) {
        return Optional.ofNullable(offenderRehabDecision).map(
                ord -> RehabDecision.builder()
                        .active(typesTransformer.ynToBoolean(ord.getActiveFlag()))
                        .comments(ord.getCommentText())
                        .decisionCode(ord.getDecisionCode())
                        .decisionDate(typesTransformer.localDateOf(ord.getDecisionDate()))
                        .offenderBookId(ord.getOffenderBookId())
                        .offenderRehabDecisionId(ord.getOffenderRehabDecisionId())
                        .recordDateTime(typesTransformer.localDateTimeOf(ord.getRecordDatetime()))
                        .staffId(ord.getStaffId())
                        .build()).orElse(null);
    }

    public RehabProvider rehabProviderOf(OffenderRehabProvider offenderRehabProvider) {
        return Optional.ofNullable(offenderRehabProvider).map(
                orp -> RehabProvider.builder()
                        .active(typesTransformer.ynToBoolean(orp.getActiveFlag()))
                        .comments(orp.getCommentText())
                        .offenderRehabProviderId(orp.getOffenderRehabProviderId())
                        .providerCode(orp.getProviderCode())
                        .build()).orElse(null);
    }
}
