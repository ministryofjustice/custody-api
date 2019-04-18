package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.gov.justice.digital.nomis.CustodyApiApplication;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class SentenceCalculationDatesCDETest {

    @Test
    public void canSerialize() throws JsonProcessingException {

        final ObjectMapper objectMapper = new CustodyApiApplication().objectMapper();
        final LocalDateTime now = LocalDateTime.now();
        String expected = "{\"apd\":\"" + now.toString() + "\"}";
        assertThat(objectMapper.writeValueAsString(new SentenceCalculationDatesCDE(SentenceCalculation.builder().apdOverridedDate(now).build()))).isEqualTo(expected);
    }

    @Test
    public void optionalDateOfBehavesCorrectly() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime later = now.plusDays(1L);
        assertThat(SentenceCalculationDatesCDE.optionalDateOf(now,later)).isEqualTo(now);
        assertThat(SentenceCalculationDatesCDE.optionalDateOf(now,null)).isEqualTo(now);
        assertThat(SentenceCalculationDatesCDE.optionalDateOf(null,later)).isEqualTo(later);
        assertThat(SentenceCalculationDatesCDE.optionalDateOf(null,null)).isNull();
    }
}