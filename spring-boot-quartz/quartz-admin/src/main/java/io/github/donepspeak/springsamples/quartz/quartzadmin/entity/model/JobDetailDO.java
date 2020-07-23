package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.model;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobDetailDO {
    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String jobClassName;
    private String isDurable;
    private String isNonconcurrent;
    private String isUpdateData;
    private String requestsRecovery;
    private String jobData;
}
