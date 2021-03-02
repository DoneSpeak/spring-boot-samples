package io.github.donespeak.pringbootsamples.event;

/**
 * @author DoneSpeak
 */

public class GetterImpl implements Getter<String> {

    @Override
    public String get() {
        return "Hahah";
    }

    @Override
    public void then(String value) {
        System.out.println(getClass().getName() + ": " + value);
    }
}