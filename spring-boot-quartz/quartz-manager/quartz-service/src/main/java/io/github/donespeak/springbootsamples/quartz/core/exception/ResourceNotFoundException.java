package io.github.donespeak.springbootsamples.quartz.core.exception;

/**
 * @author DoneSpeak
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
