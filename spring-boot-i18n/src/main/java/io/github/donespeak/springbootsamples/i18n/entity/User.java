package io.github.donespeak.springbootsamples.i18n.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class User {
    @NotNull
    @Length(min = 5, max = 12)
    @Pattern(regexp = "^r.*", message = "{validation.user.username}")
    private String username;

    @NotNull
    @Email
    private String email;

    @Range(min = 12, message = "{validation.user.age}")
    private int age;
}
