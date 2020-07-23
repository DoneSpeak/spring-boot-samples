package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author DoneSpeak
 */
@Data
public class UserVo {
    @NotNull
    @NotBlank
    private String name;
    private String avatar;
}
