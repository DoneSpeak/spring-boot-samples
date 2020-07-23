package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.exception;

import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.entity.ResourceInfo;

/**
 * @author DoneSpeak
 */
public class ResourceNotFoundApiException extends ApiException {

    private ResourceInfo resourceInfo;

    public ResourceNotFoundApiException() {
        super();
    }

    public ResourceNotFoundApiException(String message) {
        super(message);
    }

    public ResourceNotFoundApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundApiException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundApiException(ResourceInfo resourceInfo) {
        super();
        this.resourceInfo = resourceInfo;
    }

    public ResourceNotFoundApiException(ResourceInfo resourceInfo, String message) {
        super(message);
        this.resourceInfo = resourceInfo;
    }

    public ResourceNotFoundApiException(ResourceInfo resourceInfo, String message, Throwable cause) {
        super(message, cause);
        this.resourceInfo = resourceInfo;
    }

    public ResourceNotFoundApiException(ResourceInfo resourceInfo, Throwable cause) {
        super(cause);
        this.resourceInfo = resourceInfo;
    }

    public ResourceInfo getResourceInfo() {
        return resourceInfo;
    }
}
