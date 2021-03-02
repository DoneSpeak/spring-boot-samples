package io.github.donespeak.pringbootsamples.event;

/**
 * @author DoneSpeak
 */
public interface Getter<T> {
    T get();

    void then(T value);
}