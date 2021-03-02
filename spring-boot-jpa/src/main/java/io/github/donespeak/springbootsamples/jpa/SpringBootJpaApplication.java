package io.github.donespeak.springbootsamples.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;

@SpringBootApplication
public class SpringBootJpaApplication {

	public static void main(String[] args) {
		Executors.newCachedThreadPool();
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

}
