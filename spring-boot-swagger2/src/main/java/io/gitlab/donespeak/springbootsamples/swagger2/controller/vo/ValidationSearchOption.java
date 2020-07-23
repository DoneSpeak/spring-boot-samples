package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.query.ValueRange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:22
 */
@ApiModel(value = "Idea检索选项", description = "用于过滤Idea列表")
@Data
public class ValidationSearchOption {
	@ApiModelProperty(value = "Idea Id", example = "10001", required = false)
	private Long id;
	@NotBlank
	@ApiModelProperty(value = "Idean名称", example = "用香菇和抹茶做料理", required = false)
	private String name;
	@Size(max = 10)
	@ApiModelProperty(value = "状态", example = "1,2,3,4", required = false)
	private List<Integer> statuses;
	@Size(max = 10)
	private Integer[] states;

	@ApiModelProperty(value = "年龄", example = "ageRange.start=12&ageRange.end=20", required = false)
	private ValueRange<Integer> ageRange;
	@Size(max = 10)
	private Set<@NotNull Integer> ages;

	@Valid
	private IdeaVo idea;
}
