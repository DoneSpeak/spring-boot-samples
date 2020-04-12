package io.github.donespeak.pringbootsamples.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.donespeak.pringbootsamples.event.entity.TodoItem;

/**
 * @author Yang Guanrong
 * @date 2020/04/10 18:00
 */
public interface TodoRepository extends JpaRepository<TodoItem, Long> {
}
