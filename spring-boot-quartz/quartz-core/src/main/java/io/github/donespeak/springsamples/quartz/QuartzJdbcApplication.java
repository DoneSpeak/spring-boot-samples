package io.github.donespeak.springsamples.quartz;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j
@SpringBootApplication
public class QuartzJdbcApplication {

	public static void main(String[] args) {
		log.info("Start to run");
		SpringApplication.run(QuartzJdbcApplication.class, args);
	}

}
