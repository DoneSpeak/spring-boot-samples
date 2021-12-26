package io.github.donespeak.springbootsamples.i18n.support;

import io.github.donespeak.springbootsamples.i18n.core.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    private LocaleResolver localeResolver;

    private MessageSource messageSource;

    public ApiExceptionHandler(LocaleResolver localeResolver, MessageSource messageSource) {
        this.localeResolver = localeResolver;
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(ServiceException ex, HttpServletRequest request, WebRequest webRequest) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Locale locale = localeResolver.resolveLocale(request);
        log.info("the local for request is {} and the default is {}", locale, Locale.getDefault());
        String message = messageSource.getMessage("user.email.token", null, locale);
        ApiError apiError = new ApiError(ex.getCode(), message);
        log.info("The result error of request {} is {}", request.getServletPath(), ex.getMessage(), ex);
        return new ResponseEntity(apiError, headers, status);
    }
}
