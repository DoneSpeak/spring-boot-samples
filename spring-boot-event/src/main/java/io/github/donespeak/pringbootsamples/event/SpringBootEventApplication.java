package io.github.donespeak.pringbootsamples.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootEventApplication {

	public static void main(String[] args) throws Exception {
		try {
			SpringApplication.run(SpringBootEventApplication.class, args);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		}
	}
}
