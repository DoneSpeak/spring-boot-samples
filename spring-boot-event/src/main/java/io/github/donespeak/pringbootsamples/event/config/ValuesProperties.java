package io.github.donespeak.pringbootsamples.event.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "values")
public class ValuesProperties {

    private Map<String, String> mapping;
}
