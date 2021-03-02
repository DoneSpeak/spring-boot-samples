package io.github.donespeak.springbootsamples.intercept.util;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

/**
 * @author DoneSpeak
 */
public class RequestResponseLogger {

    private Logger log;

    private int contentLengthLimit = 1024 * 5;

    public RequestResponseLogger(Logger log) {
        this.log = log;
    }

    public void log(HttpServletRequest request) {
        log(request, "");
    }

    public void log(HttpServletRequest request, String message) {
        String requestBody = null;
        try {
            requestBody = getRequestBody(request);
        } catch (IOException e) {
            // nothing
        }
        log.info("request: {} {}?{} {}, {}", request.getMethod(), request.getRequestURI(), mapParameters(request), requestBody, message);
    }

    public void log(HttpServletResponse response) {
        log(response, "");
    }

    public void log(HttpServletResponse response, String message) {
        String responseBody = getResponsePayload(response);
        log.info("response: {} {} {}, {}", response.getStatus(), response.getHeaderNames(), responseBody, message);
    }

    public void log(HttpServletRequest request, HttpServletResponse response) {
        log(request);
        log(response);
    }

    private String getResponseBody(HttpServletResponse response) {
        // 无法获取到 OutputStream中的内容
        return "";
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        if (request.getInputStream().isFinished()) {
            return "";
        }

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        IOUtils.copy(request.getInputStream(), out, contentLengthLimit);
//        return new String(out.toByteArray());
        return "";
    }

    private String mapParameters(HttpServletRequest request) {
        return request.getParameterMap().entrySet().stream().map(en -> en.getKey() + "=" + StringUtils.join(en.getValue())).collect(Collectors.joining("&"));
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";
    }

}
