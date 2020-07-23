package io.github.donepspeak.springsamples.quartz.quartzadmin.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DoneSpeak
 */
@ApiModel(value = "trigger的key", description = "可确定一下trigger")
@Data
public class TriggerKeyDto {
    @ApiModelProperty(value = "scheduler的名称", example = "defaultScheduler", required = true)
    private String schedName;
    @ApiModelProperty(value = "trigger所在组", example = "DEFAULT_GROUP", required = true)
    private String triggerGroup;
    @ApiModelProperty(value = "trigger的名称", example = "07811f96-a6d9-11ea-bb37-0242ac130002", required = true)
    private String triggerName;
}
