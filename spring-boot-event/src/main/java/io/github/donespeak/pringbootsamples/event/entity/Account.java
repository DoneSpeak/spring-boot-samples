package io.github.donespeak.pringbootsamples.event.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 10:48
 */
@Data
@Entity(name = "account_account")
public class Account {
    @Id
    @GeneratedValue
	private Long id;

    @NotBlank
    @Column(nullable = false)
	private String firstName;

    @NotBlank
    @Column(nullable = false)
	private String lastName;

    @NotBlank
    @Column(nullable = false)
	private String email;

    @NotBlank
    @Column(nullable = false)
	private String password;
}
