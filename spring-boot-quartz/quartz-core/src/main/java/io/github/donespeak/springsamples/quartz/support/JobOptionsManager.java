package io.github.donespeak.springsamples.quartz.support;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.quartz.Job;

/**
 * @author DoneSpeak
 */
public class JobOptionsManager {

    private static List<String> jobOptions = new CopyOnWriteArrayList<>();

    public static void add(Class<? extends Job> jobClass) {
        synchronized (JobOptionsManager.class) {
            jobOptions.add(jobClass.getName());
            Collections.sort(jobOptions);
        }
    }
}
