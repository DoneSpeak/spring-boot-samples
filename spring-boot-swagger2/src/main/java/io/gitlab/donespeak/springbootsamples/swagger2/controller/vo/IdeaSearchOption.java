package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:22
 */
@ApiModel(value = "Idea检索选项", description = "用于过滤Idea列表")
@Data
public class IdeaSearchOption {
	@ApiModelProperty(value = "Idea Id", example = "10001", required = false)
	private Long id;
	@ApiModelProperty(value = "Idean名称", example = "用香菇和抹茶做料理", required = false)
	private String name;
}
