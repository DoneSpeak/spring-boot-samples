package io.gitlab.donespeak.tutorial.springboottest.entity;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 15:56
 */
@Data
public class FileInfo {
	private String id;

	public FileInfo(String id) {
		this.id = id;
	}
}
