package io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.entity;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class ResourceInfo {
    /**
     * 资源类型，比如：User
     */
    private String resourceType;
    /**
     * 资源名称，比如: doenspeak
     */
    private String resourceName;

    public ResourceInfo() {}

    public ResourceInfo(String resourceType, String resourceName) {
        this.resourceType = resourceType;
        this.resourceName = resourceName;
    }
}
