package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author DoneSpeak
 */
@Data
public class IdeaAttachment {
    @NotBlank
    private String url;
    @NotBlank
    private String name;
}