package io.gitlab.donespeak.springbootsamples.swagger2.support;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/23
 */
@Data
public class ResponseWrapper {
	private String code;
	private String message;
	private Object data;
}
