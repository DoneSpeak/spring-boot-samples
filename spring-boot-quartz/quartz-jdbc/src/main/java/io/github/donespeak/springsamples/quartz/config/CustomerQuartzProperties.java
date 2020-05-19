package io.github.donespeak.springsamples.quartz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DoneSpeak
 */
@Data
@ConfigurationProperties("myapp.quartz")
public class CustomerQuartzProperties {

    private String imageSchedulerName = "default_image_scheduler";
}
