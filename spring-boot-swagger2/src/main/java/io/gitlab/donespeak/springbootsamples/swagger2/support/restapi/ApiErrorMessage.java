package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi;

import org.springframework.http.HttpStatus;

/**
 * @author DoneSpeak
 */
public class ApiErrorMessage {
    private HttpStatus httpStatus;
    private String message;

    public ApiErrorMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus.value();
    }

    public String getMessage() {
        return message;
    }
}
