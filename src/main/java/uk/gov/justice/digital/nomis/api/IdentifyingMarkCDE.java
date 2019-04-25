package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IdentifyingMarkCDE {
    private List<IdentifyingMark> HEAD;
    private List<IdentifyingMark> BODY;
}
