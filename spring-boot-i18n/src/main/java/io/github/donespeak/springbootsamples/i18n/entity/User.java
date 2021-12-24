package io.github.donespeak.springbootsamples.i18n.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class User {
    @NotNull
    @Length(min = 5, max = 12)
    private String username;
}
