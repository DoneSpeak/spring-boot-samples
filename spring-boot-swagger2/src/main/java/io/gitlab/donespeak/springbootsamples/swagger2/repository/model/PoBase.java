package io.gitlab.donespeak.springbootsamples.swagger2.repository.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * TODO 需要研究一下 MybatisPlus和JPA的实现方案
 * @author Yang Guanrong
 * @date 2020/03/17 01:33
 */
@Data
public class PoBase implements Po {
	/**
	 * 创建时间
	 */
	private LocalDate createAt;
	/**
	 * 更新时间
	 */
	private LocalDate updateAt;
	/**
	 * 逻辑删除
	 */
	private boolean deleted;
}
