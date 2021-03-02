package io.github.donespeak.springbootsamples.intercept.filter;

import io.github.donespeak.springbootsamples.intercept.util.RequestResponseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DoneSpeak
 */
//@Component
public class HttpRequestLoggingFilter extends AbstractRequestLoggingFilter {

    private RequestResponseLogger logger = new RequestResponseLogger(LoggerFactory.getLogger(HttpRequestLoggingFilter.class));

    public HttpRequestLoggingFilter() {
        setIncludePayload(true);
        setMaxPayloadLength(1024 * 5);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        this.getServletContext().log(message);
//        logger.log(request, message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        this.getServletContext().log(message);
//        logger.log(request, message);
    }
}
