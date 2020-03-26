package io.gitlab.donespeak.springbootsamples.swagger2.support;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yang Guanrong
 * @date 2020/03/23
 */
@Data
@NoArgsConstructor
public class ValidError {
	/**
	 * @see ValidErrorType
	 */
	private String type;
	/**
	 * 校验不通过的属性名
	 */
	private String field;
	/**
	 * 具体的错误提示
	 */
	private String message;

	public ValidError(String type, String field, String message) {
		this.type = type;
		this.field = field;
		this.message = message;
	}
}
