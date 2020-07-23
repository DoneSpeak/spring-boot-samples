package io.gitlab.donespeak.springbootsamples.swagger2.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author DoneSpeak
 */
@Data
public class ValidationBodyItem {
    @Length(max = 12)
    private String str;
}
