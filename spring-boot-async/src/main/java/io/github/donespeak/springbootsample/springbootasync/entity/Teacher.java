package io.github.donespeak.springbootsample.springbootasync.entity;

import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Component("teacher")
public class Teacher implements Human, Career {
    @Override
    public int getYears() {
        return 0;
    }

    @Override
    public int getAge() {
        return 0;
    }
}
