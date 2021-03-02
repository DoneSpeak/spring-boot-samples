package io.github.donespeak.springbootsamples.jpa.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DoneSpeak
 */
@RestController
@RequestMapping("/tmp")
public class TempController {

    @GetMapping("/request/**")
    public Object request(HttpServletRequest req) {
        RequestPart part = new RequestPart();
        part.setSchema(req.getScheme());
        part.setPort(req.getServerPort() + "");
        part.setContextPath(req.getContextPath());
        part.setServerName(req.getServerName());
        part.setRequestUri(req.getRequestURI());
        part.setQueryString(req.getQueryString());
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            part.addHeader(name, value);
        }
        part.setParams(req.getParameterMap());

        return part;
    }

    @Data
    public static class RequestPart {
        private String schema;
        private String port;
        private String serverName;
        private String contextPath;
        private String requestUri;
        private String queryString;
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String[]> params = new HashMap<>();

        public void addHeader(String name, String value) {
            headers.put(name, value);
        }
    }
}
