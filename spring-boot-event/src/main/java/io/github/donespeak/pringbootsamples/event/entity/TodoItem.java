package io.github.donespeak.pringbootsamples.event.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Yang Guanrong
 * @date 2020/03/05 02:15
 */
@Data
@Entity(name = "todo_todo")
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String content;

    @NotNull
    @Column(nullable = false)
    private Long createdBy;

    @Column
    private int status;

    @Column
    private boolean deleted;

    @Column
    private Date gmtCreated;

    @Column
    private Date gmtModified;
}
