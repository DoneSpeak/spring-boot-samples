package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.exception;

import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.ApiValidError;

import java.util.List;

/**
 * @author DoneSpeak
 */
public class ValidArgumentException extends ApiException {

    private List<ApiValidError> errors;

    public ValidArgumentException(List<ApiValidError> errors) {
        super();
        this.errors = errors;
    }

    public ValidArgumentException(List<ApiValidError> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public ValidArgumentException(List<ApiValidError> errors, String message, Throwable cause) {
        super(message, cause);
        this.errors = errors;
    }

    public ValidArgumentException(List<ApiValidError> errors, Throwable cause) {
        super(cause);
        this.errors = errors;
    }

    public List<ApiValidError> getErrors() {
        return errors;
    }
}
