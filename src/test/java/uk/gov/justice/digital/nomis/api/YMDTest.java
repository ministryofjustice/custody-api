package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.gov.justice.digital.nomis.CustodyApiApplication;

import static org.assertj.core.api.Assertions.assertThat;


public class YMDTest {

    @Test
    public void canSerialize() throws JsonProcessingException {

        final ObjectMapper objectMapper = new CustodyApiApplication().objectMapper();
        YMD ymd = new YMD("10/6/5");

        String expected = "{\"years\":10,\"months\":6,\"days\":5}";
        assertThat(objectMapper.writeValueAsString(ymd)).isEqualTo(expected);
    }
}