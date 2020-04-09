package io.github.donespeak.springbootsamples.intercept.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author Yang Guanrong
 * @date 2020/04/04 09:38
 */
@Configuration
public class ErrorPageConfig {

	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		ErrorPage[] errorPages = new ErrorPage[] {
			new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"),
			new ErrorPage(HttpStatus.FORBIDDEN, "/403.html"),
			new ErrorPage(HttpStatus.UNAUTHORIZED, "/402.html")
		};

		return (factory -> factory.addErrorPages(errorPages));
	}
}
