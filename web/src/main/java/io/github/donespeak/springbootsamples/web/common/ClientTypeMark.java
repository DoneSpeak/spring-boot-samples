package io.github.donespeak.springbootsamples.web.common;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClientTypeMark {
}
