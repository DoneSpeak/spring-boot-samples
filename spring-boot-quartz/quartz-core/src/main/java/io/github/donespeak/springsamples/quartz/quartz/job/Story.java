package io.github.donespeak.springsamples.quartz.quartz.job;

import java.io.Serializable;

/**
 * @author DoneSpeak
 */
public class Story implements Serializable {

    private static final long serialVersionUID = -2933035971354232870L;

    private byte[] pictrue;
    private String message;

    public Story() {}

    public byte[] getPictrue() {
        return pictrue;
    }

    public void setPictrue(byte[] pictrue) {
        this.pictrue = pictrue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
