package io.github.donespeak.springbootsamples.web.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurClientTypeMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final String[] CLIENT_TYPE_HEADER_NAMES = {"client-type", "CLIENT-TYPE", "Client-Type"};

    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.hasParameterAnnotation(ClientTypeMark.class)
                && ClientType.class.isAssignableFrom(param.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String clientType = null;
        for (String clientTypeHeader: CLIENT_TYPE_HEADER_NAMES) {
            clientType = nativeWebRequest.getHeader(clientTypeHeader);
            if (StringUtils.isNotBlank(clientType)) {
                break;
            }
        }
        try {
            return StringUtils.isBlank(clientType) ? ClientType.UNKNOWN : ClientType.valueOf(clientType.toUpperCase());
        } catch (Exception e) {
            return ClientType.UNKNOWN;
        }
    }
}
