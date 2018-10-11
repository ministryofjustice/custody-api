package uk.gov.justice.digital.nomis.jms;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import oracle.jms.AQjmsMapMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.mongodb.entity.XtagEvent;
import uk.gov.justice.digital.nomis.mongodb.repository.XtagEventRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Optional;

@Component
@Slf4j
@Profile("oracle")
public class XtagEventsListener {

    private final XtagEventRepository xtagEventRepository;

    public XtagEventsListener(XtagEventRepository xtagEventRepository) {
        this.xtagEventRepository = xtagEventRepository;
    }

    @JmsListener(destination = "XTAG.XTAG_OUT", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onMessage(Message message) throws JMSException {
        log.info("Handling XTAG event : {}", message.getJMSMessageID());

        Optional<XtagEvent> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagEventRepository::save);
    }

    private Optional<XtagEvent> asXtagEvent(Message message) {

        final Optional<AQjmsMapMessage> maybeAqJmsMapMessage = Optional.ofNullable(message)
                .filter(m -> m instanceof AQjmsMapMessage)
                .map(m -> (AQjmsMapMessage) m);

        if (!maybeAqJmsMapMessage.isPresent()) {
            log.error("Don't know what this is: {}", message.getClass().getName());
        }

        return maybeAqJmsMapMessage.flatMap(msg -> {
            try {
                final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
                final Enumeration mapNames = msg.getMapNames();
                while (mapNames.hasMoreElements()) {
                    final String key = mapNames.nextElement().toString();
                    builder.put(key, msg.getObject(key).toString());
                }

                return Optional.of(XtagEvent.builder()
                        .content(builder.build())
                        .eventType(msg.getJMSType())
                        .nomisTimestamp(LocalDateTime.now())
                        .build());
            } catch (JMSException e) {
                log.error(e.getMessage());
                return Optional.empty();
            }
        });
    }

    @JmsListener(destination = "XTAG.XTAG_STALE", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onStaleMessage(Message message) throws JMSException {
        log.info("Handling XTAG_STALE event : {}", message.getJMSMessageID());

        Optional<XtagEvent> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagEventRepository::save);

    }

    @JmsListener(destination = "XTAG.XTAG_UPD_OFFENDERS", concurrency = "1", containerFactory = "aqListenerContainerFactory")
    public void onOffenderUpdMessage(Message message) throws JMSException {
        log.info("Handling XTAG_UPD_OFFENDER EVENT {}", message.getJMSMessageID());

        Optional<XtagEvent> maybeXtagEvent = asXtagEvent(message);

        maybeXtagEvent.ifPresent(xtagEventRepository::save);
    }
}
