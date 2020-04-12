package io.github.donespeak.pringbootsamples.event.core.exception;

/**
 * @author Yang Guanrong
 * @date 2020/04/10 18:17
 */
public class ServiceException extends RuntimeException {
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
