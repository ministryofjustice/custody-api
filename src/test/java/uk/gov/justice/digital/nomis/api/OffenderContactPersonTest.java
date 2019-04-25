package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import uk.gov.justice.digital.nomis.CustodyApiApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class OffenderContactPersonTest {

    @Test
    public void canSerializeWithoutPrimaryAddress() {
        var ocp = OffenderContactPerson.builder()
                .addresses(ImmutableList.of(Address.builder()
                        .street("Acacia Ave")
                        .addressType("HOME")
                        .relationship(IdPair.builder().key("key").value("value").build())
                        .build()))
                .build();

        var objectMapper = new CustodyApiApplication().objectMapper();

        try {
            var s = objectMapper.writeValueAsString(ocp);
            assertThat(s.contains("addresses")).isTrue();
            assertThat(s.contains("primaryAddress")).isFalse();
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void canSerializeWithPrimaryAddress() {
        var ocp = OffenderContactPerson.builder()
                .addresses(ImmutableList.of(Address.builder()
                        .street("Acacia Ave")
                        .addressType("HOME")
                        .relationship(IdPair.builder().key("key").value("value").build())
                        .build()))
                .primaryAddressBias("HOME")
                .build();

        var objectMapper = new CustodyApiApplication().objectMapper();

        try {
            var s = objectMapper.writeValueAsString(ocp);
            assertThat(s.contains("addresses")).isTrue();
            assertThat(s.contains("primaryAddress")).isTrue();
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }

}