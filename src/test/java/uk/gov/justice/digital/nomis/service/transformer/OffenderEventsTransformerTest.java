package uk.gov.justice.digital.nomis.service.transformer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import uk.gov.justice.digital.nomis.CustodyApiApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class OffenderEventsTransformerTest {

    @Test
    public void canDeserializeIntoXtagContent() {

        final ObjectMapper objectMapper = new CustodyApiApplication().objectMapper();

        OffenderEventsTransformer transformer = new OffenderEventsTransformer(mock(TypesTransformer.class), objectMapper);

        assertThat(transformer.xtagContentOf(ImmutableMap.of("x", "y"))).isNotNull();
    }

}