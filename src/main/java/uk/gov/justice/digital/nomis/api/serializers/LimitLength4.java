package uk.gov.justice.digital.nomis.api.serializers;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

public class LimitLength4 extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.substring(0, Math.min(value.length(), 4)));
    }
}
