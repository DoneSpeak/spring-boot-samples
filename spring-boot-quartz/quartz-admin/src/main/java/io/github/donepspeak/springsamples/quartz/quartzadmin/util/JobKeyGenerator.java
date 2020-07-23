package io.github.donepspeak.springsamples.quartz.quartzadmin.util;

import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.util.UUID;

/**
 * @author DoneSpeak
 */
public class JobKeyGenerator {

    public JobKey generateJobKey(Class<? extends Job> jobClass) {
        return new JobKey(uuid(), jobClass.getSimpleName());
    }

    public TriggerKey generateTriggerKey(Class<? extends Job> jobClass) {
        return new TriggerKey(uuid(), jobClass.getSimpleName());
    }

    private String uuid() {
        return UUID.randomUUID().toString();
    }
}
