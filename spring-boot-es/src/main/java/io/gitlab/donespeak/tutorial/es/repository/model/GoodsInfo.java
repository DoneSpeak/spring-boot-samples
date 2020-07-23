package io.gitlab.donespeak.tutorial.es.repository.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author DoneSpeak
 */
@Data
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -4642147796783993827L;

    private String id;
    private String name;
    private String caption;
}
