package io.github.donespeak.pringbootsamples.event.entity;

import lombok.Data;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:15
 */
@Data
public class TodoItem {
    private long id;
    private String message;
    private int status;
}
