package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author DoneSpeak
 */
@Data
@ApiModel(value = "IdeaVoCreateUpdate")
public class IdeaVoCreateUpdate extends IdeaVo {

    @ApiModelProperty(value = "updateAt", example = "2001-02-22", readOnly = true)
    private LocalDate updateAt;

    @NotNull
    @ApiModelProperty(value = "updateAt", example = "2001-02-22", readOnly = true)
    private String tips;

    @Override
    @ApiModelProperty(hidden = true)
    public long getId() {
        return super.getId();
    }


}
