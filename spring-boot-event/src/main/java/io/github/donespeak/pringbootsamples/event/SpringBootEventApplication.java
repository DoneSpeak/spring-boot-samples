package io.github.donespeak.pringbootsamples.event;

import io.github.donespeak.pringbootsamples.event.listener.todo.TodoEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEventApplication.class, args);
	}

}
