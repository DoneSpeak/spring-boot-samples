package io.github.donespeak.springbootsample.springbootasync.entity;

import org.springframework.stereotype.Component;

/**
 * @author DoneSpeak
 */
@Component("student")
public class Student implements Career, Human {

    @Override
    public int getYears() {
        return 0;
    }

    @Override
    public int getAge() {
        return 0;
    }
}
