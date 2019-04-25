package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalsCDE {
    private Long bookingId;
    private IdentifyingMarkCDE identifyingMarks;
    private List<PhysicalAttribute> physicalAttributes;
    private Map<String,KeyValue> profileDetails;


}
