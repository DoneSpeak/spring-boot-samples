package io.github.donepspeak.springsamples.quartz.quartzadmin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Data
@Component
@ConfigurationProperties(prefix = "quartz-admin")
public class QuartzAdminProperties {
    private QuartzAdminContextProperties context;
}
