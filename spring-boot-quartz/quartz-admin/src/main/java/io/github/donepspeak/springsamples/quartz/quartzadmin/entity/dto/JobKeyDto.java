package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class JobKeyDto {
    private String schedName;
    private String jobGroup;
    private String jobName;

    public JobKeyDto() {}

    public JobKeyDto(String schedName, String jobGroup, String jobName) {
        this.schedName = schedName;
        this.jobGroup = jobGroup;
        this.jobName = jobName;
    }
}
