package io.github.donespeak.springbootsamples.intercept.filter;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 相关springframework issue:
 * <li>[ContentCachingRequestWrapper cache input stream [SPR-16028] #20577](https://github.com/spring-projects/spring-framework/issues/20577)
 *
 * @author DoneSpeak
 */
//@Component
public class HttpRequestResponseLoggingFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(HttpRequestResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {
            // 系统异常（如抛出RuntimeException）不会进入到这里，response.getStatus()获取到的值为200，而不是500

            // requestWrapper.getContentAsByteArray() 只有在流的读取方法被调用时才会有值，且最大为{@Code contentCacheLimit} byte
            String requestParam = Optional.ofNullable(requestWrapper.getParameterMap()).map(m -> mapParameters(m)).orElse("");
            String requestBody = IOUtils.toString(requestWrapper.getContentAsByteArray(), "utf-8");
            String responseBody = IOUtils.toString(responseWrapper.getContentAsByteArray(), "utf-8");
            // Do not forget this line after reading response content or actual response will be empty!
            responseWrapper.copyBodyToResponse();
            // Write request and response body, headers, timestamps etc. to log files
            log.info("requestParam: {}", requestParam);
            log.info("responseBody: {}", responseBody);
        }
    }

    private String mapParameters(Map<String, String[]> m) {
        return m.entrySet().stream().map(en -> en.getKey() + "=" + StringUtils.join(en.getValue())).collect(Collectors.joining("&"));
    }
}
