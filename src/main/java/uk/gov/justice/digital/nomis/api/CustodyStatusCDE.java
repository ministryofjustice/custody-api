package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Value;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import uk.gov.justice.digital.nomis.api.serializers.LimitLength4;

@Value
@Builder
public class CustodyStatusCDE {
    @JsonSerialize(using = LimitLength4.class)
    @Builder.Default
    String statusReason = "";

    @Builder.Default
    Boolean inTransit = false;

    @Builder.Default
    Boolean isActive = false;

    Long bookingSequence;

    @Builder.Default
    String inOutStatus = "";
}
