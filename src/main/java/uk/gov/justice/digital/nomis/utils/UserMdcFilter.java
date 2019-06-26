package uk.gov.justice.digital.nomis.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static uk.gov.justice.digital.nomis.utils.MdcUtility.USER_ID_HEADER;

@Slf4j
@Component
@Order(1)
public class UserMdcFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) {
        // Initialise - no functionality
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        final var currentUsername = getUser((HttpServletRequest) request);

        try {
            if (currentUsername != null) {
                MDC.put(USER_ID_HEADER, currentUsername);
            }
            chain.doFilter(request, response);
        } finally {
            if (currentUsername != null) {
                MDC.remove(USER_ID_HEADER);
            }
        }
    }

    @Override
    public void destroy() {
        // Destroy - no functionality
    }

    private String getUser(final HttpServletRequest req) {
        return req.getUserPrincipal() != null ? req.getUserPrincipal().getName() : null;
    }
}
