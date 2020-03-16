package io.gitlab.donespeak.springbootsamples.swagger2.repository.model;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:32
 */
@Data
public class Idea extends PoBase {
	private long id;
	private String name;
	private String caption;
}
