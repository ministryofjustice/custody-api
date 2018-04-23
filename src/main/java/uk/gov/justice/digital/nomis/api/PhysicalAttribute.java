package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhysicalAttribute {
    private Long attributeSeq;
    private Integer heightFeet;
    private Integer heightInches;
    private Integer heightCm;
    private Integer weightLbs;
    private Integer weightKg;
}
