package uk.gov.justice.digital.nomis.info;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;

import static org.springframework.http.MediaType.*;

@Configuration
@WebEndpoint(id = "ping")
public class PingEndpoint {
    @ReadOperation(produces = TEXT_PLAIN_VALUE)
    public String ping() {
        return "pong";
    }
}
