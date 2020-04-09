package io.github.donespeak.pringbootsamples.event.entity;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:48
 */
@Data
public class Account {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
