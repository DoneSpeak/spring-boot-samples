package io.gitlab.donespeak.springbootsamples.swagger2.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author DoneSpeak
 */
@Data
public class ValidationBody {
    @NotBlank
    private String str;
    @Size(max = 10)
    private List<@Length(min = 2) String> strList;
    @Size(max = 10)
    private String[] strArr;
    @Size(max = 10)
    private Set<@Length(min = 2) @NotNull String> set;
    @Valid
    private ValidationBodyItem obj;
    @Size(max = 10)
    private List<@NotNull @Valid ValidationBodyItem> objList;
    @Max(100)
    private int intVal;
    @Size(max = 3)
    private Map<@Length(min = 2) String, @NotBlank String> map;

    private Map<String, @Valid ValidationBodyItem> itemMap;
}