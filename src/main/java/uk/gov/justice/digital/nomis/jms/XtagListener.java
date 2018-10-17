package uk.gov.justice.digital.nomis.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import oracle.jms.AQjmsMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.mongodb.entity.Xtag;
import uk.gov.justice.digital.nomis.mongodb.entity.XtagContent;
import uk.gov.justice.digital.nomis.mongodb.repository.XtagRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Optional;

@Component
@Slf4j
@Profile({"oracle", "integrated"})
public class XtagListener {

    private final XtagRepository xtagRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public XtagListener(XtagRepository xtagRepository, ObjectMapper objectMapper) {
        this.xtagRepository = xtagRepository;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "XTAG.XTAG_OUT", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onMessage(Message message) throws JMSException {
        log.info("Handling XTAG event : {}", message.getJMSMessageID());

        Optional<Xtag> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagRepository::save);
    }

    private Optional<Xtag> asXtagEvent(Message message) {
        final Optional<AQjmsMapMessage> maybeAqJmsMapMessage = Optional.ofNullable(message)
                .filter(m -> m instanceof AQjmsMapMessage)
                .map(m -> (AQjmsMapMessage) m);

        if (!maybeAqJmsMapMessage.isPresent()) {
            log.error("Don't know what this is: {}", message.getClass().getName());
        }

        return maybeAqJmsMapMessage.flatMap(msg -> {
            XtagContent xtagContent = xtagContentOf(msg);

            return Optional.ofNullable(xtagContent != null ? Xtag.builder()
                    .content(xtagContent)
                    .eventType(xtagContent.getXtag())
                    .nomisTimestamp(Optional.ofNullable(xtagContent.getP_nomis_timestamp())
                            .map(x -> LocalDateTime.parse(x, DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSSSSSSSS")))
                            .orElse(LocalDateTime.now()))
                    .build() : null);
        });
    }

    @JmsListener(destination = "XTAG.XTAG_STALE", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onStaleMessage(Message message) throws JMSException {
        log.info("Handling XTAG_STALE event : {}", message.getJMSMessageID());

        Optional<Xtag> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagRepository::save);
    }

    @JmsListener(destination = "XTAG.XTAG_UPD_OFFENDERS", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onOffenderUpdMessage(Message message) throws JMSException {
        log.info("Handling XTAG_UPD_OFFENDER EVENT {}", message.getJMSMessageID());

        Optional<Xtag> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagRepository::save);
    }

    private XtagContent xtagContentOf(AQjmsMapMessage msg) {
        ImmutableMap<String, String> map = mapOf(msg);

        try {
            String stringValue = objectMapper.writeValueAsString(map);
            return objectMapper.readValue(stringValue, XtagContent.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private ImmutableMap<String, String> mapOf(AQjmsMapMessage msg) {
        final ImmutableMap.Builder<String, String> mapBuilder = ImmutableMap.builder();
        try {
            mapBuilder.put("xtag", msg.getJMSType());

            final Enumeration mapNames = msg.getMapNames();
            while (mapNames.hasMoreElements()) {
                final String key = mapNames.nextElement().toString();
                mapBuilder.put(key, msg.getObject(key).toString());
            }
        } catch (JMSException e) {
            log.error(e.getMessage());
        }
        return mapBuilder.build();
    }
}
