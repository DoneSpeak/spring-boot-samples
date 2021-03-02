package io.github.donespeak.pringbootsamples.event;

import java.util.Arrays;
import java.util.List;

/**
 * @author DoneSpeak
 */
public class TempTest implements Getter<Exception> {

    public static void main(String[] args) throws ClassNotFoundException {
        getter().forEach(getter -> getter.then(getter.get()));
    }

    public static List<Getter> getter() {
        return Arrays.asList(new GetterImpl(), new TempTest());
    }

    @Override
    public Exception get() {
        return new Exception("xixi");
    }

    @Override
    public void then(Exception value) {
        System.out.println(getClass() + ": ex(" + value.getMessage() + ")");
        value.printStackTrace();
    }
}
