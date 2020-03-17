package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:20
 */
@ApiModel
@Data
public class IdeaVo {
    @ApiModelProperty(value = "id", example = "10001", required = true)
	private long id;
    @ApiModelProperty(value = "名称", example = "用香菇和抹茶做料理", required = true)
	private String name;
    @ApiModelProperty(value = "内容", example = "也许可以做一个香菇抹茶三明治", required = true)
	private String caption;
    @ApiModelProperty(value = "状态", example = "1", required = true)
    private int status;
    @ApiModelProperty(value = "图片", example = "https//xxx.xxx.xx/xxx/xxxxx", required = true)
    private String img;
    @ApiModelProperty(value = "附件", example = "https//xxx.xxx.xx/xxx/yyyyy", required = true)
    private String attachment;
}
