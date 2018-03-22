package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class TypesTransformer {

    public LocalDate localDateOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp).map(t -> t.toLocalDateTime().toLocalDate()).orElse(null);
    }

    public LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp).map(Timestamp::toLocalDateTime).orElse(null);
    }

    public Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn).map("Y"::equalsIgnoreCase).orElse(null);
    }

}