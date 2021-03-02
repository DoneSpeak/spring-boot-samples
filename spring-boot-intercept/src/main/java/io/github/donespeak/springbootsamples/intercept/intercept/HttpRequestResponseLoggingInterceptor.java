package io.github.donespeak.springbootsamples.intercept.intercept;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DoneSpeak
 */
//@Component
public class HttpRequestResponseLoggingInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(HttpRequestResponseLoggingInterceptor.class);

    private int contentLengthLimit = 1024 * 5;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestBody = getRequestBody(request);
        log.info("preHandle request: {} {}?{} {}", request.getMethod(), request.getRequestURI(), mapParameters(request), requestBody);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        // 流已经关闭
        String requestBody = "";//getRequestBody(request);
        // OutputStream无法读取
        String responseBody = getResponseBody(response);
        // 发生错误的时候，会在内部进行转发到 /error，出现一次请求经过：preHandle -> preHandle(/error) -> postHandle(/error)
        log.info("postHandle request: {} {}?{} {}", request.getMethod(), request.getRequestURI(), mapParameters(request), requestBody);
        log.info("postHandle response: {} {} {}", response.getStatus(), response.getHeaderNames(), responseBody);
    }

    private String getResponseBody(HttpServletResponse response) {
        // 无法获取到 OutputStream中的内容
        return "";
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), out, contentLengthLimit);
        return new String(out.toByteArray());
    }

    private String mapParameters(HttpServletRequest request) {
        return request.getParameterMap().entrySet().stream().map(en -> en.getKey() + "=" + StringUtils.join(en.getValue())).collect(Collectors.joining("&"));
    }

    public HttpRequestResponseLoggingInterceptor() {
        super();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        // 发生异常时，因为转发到了 /error，所以request中输出的为/error
        log.info("afterCompletion request: {}", request.getRequestURI());
        log.info("afterCompletion response: {}", response.getStatus());
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
        log.info("afterConcurrentHandlingStarted request: {}", request.getRequestURI());
        log.info("afterConcurrentHandlingStarted response: {}", response.getStatus());
    }
}
