package io.github.donespeak.springbootsamples.intercept.exception;

/**
 * @author DoneSpeak
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4972821311406658933L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
