package io.gitlab.donespeak.springbootsamples.swagger2.controller.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:20
 */
@ApiModel(value = "IdeaVo")
@Data
public class IdeaVo {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "id", example = "10001", readOnly = true)
    private long id;
    @NotNull
    @Length(min = 4, max = 50)
    @ApiModelProperty(value = "名称", example = "用香菇和抹茶做料理", required = true)
    private String name;
    @NotNull
    @Max(400)
    @ApiModelProperty(value = "内容 | 最长400", example = "也许可以做一个香菇抹茶三明治", required = true)
    private String caption;
    @ApiModelProperty(value = "状态", example = "1", required = true)
    private int status;
    @NotBlank
    @ApiModelProperty(value = "图片", example = "https//xxx.xxx.xx/xxx/xxxxx", required = true)
    private String img;
    @NotEmpty
    @ApiModelProperty(value = "附件", example = "https//xxx.xxx.xx/xxx/yyyyy", required = true)
    private List<@Valid IdeaAttachment> attachments;
    @Valid
    @ApiModelProperty(value = "附件", example = "https//xxx.xxx.xx/xxx/yyyyy", required = true)
    private UserVo creator;
    @ApiModelProperty(value = "创建时间", example = "2001-01-01", readOnly = true)
    private LocalDate createAt;
}
