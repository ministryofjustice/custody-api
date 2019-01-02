package uk.gov.justice.digital.nomis.api;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdPair {
    private String key;
    private Object value;

    public Map<String, Object> asMap() {
        return ImmutableMap.of(key, value);
    }
}
