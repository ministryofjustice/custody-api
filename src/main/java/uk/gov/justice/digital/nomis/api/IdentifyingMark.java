package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdentifyingMark {
    private Long idMarkSeq;
    private String bodyPartCode;
    private String markType;
    private String sideCode;
    private String partOrientationCode;
    private String comments;
}
