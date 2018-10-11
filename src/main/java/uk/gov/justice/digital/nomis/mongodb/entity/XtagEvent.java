package uk.gov.justice.digital.nomis.mongodb.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Document
public class XtagEvent {
    @Id
    private String id;
    @Indexed(name = "nomisTimestampIndex", expireAfterSeconds = 60 * 60 * 12 * 4)
    private LocalDateTime nomisTimestamp;
    private Map<String, String> content;
    private String eventType;
}
