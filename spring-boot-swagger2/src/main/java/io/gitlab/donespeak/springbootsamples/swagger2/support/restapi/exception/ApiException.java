package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.exception;

/**
 * @author DoneSpeak
 */
public class ApiException extends RuntimeException {

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

}
