package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.jpa.entity.LabelledTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class TypesTransformer {

    public LocalDate localDateOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(t -> t.toLocalDateTime().toLocalDate())
                .orElse(null);
    }

    public LocalDate localDateOf(LabelledTimestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(t -> t.getTimestamp().toLocalDateTime().toLocalDate())
                .orElse(null);
    }

    public LocalDate localDateOf(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .orElse(null);
    }

    public LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
    }

    public LocalTime localTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .map(LocalDateTime::toLocalTime)
                .orElse(null);
    }

    public LocalDateTime localDateTimeOf(Timestamp date, Timestamp time) {
        return Optional.ofNullable(date)
                .map(Timestamp::toLocalDateTime)
                .map(dateTime ->
                        Optional.ofNullable(time)
                            .map(t -> t.toLocalDateTime().toLocalTime().atDate(dateTime.toLocalDate()))
                            .orElse(dateTime))
                .orElse(null);
    }
    public static LocalDateTime localDateTimeOf(String localDate) {
        if (localDate.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(localDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn)
                .map("Y"::equalsIgnoreCase)
                .orElse(false);
    }

    public Boolean isActiveOf(String active) {
        return Optional.ofNullable(active)
                .map("A"::equalsIgnoreCase)
                .orElse(false);
    }
}