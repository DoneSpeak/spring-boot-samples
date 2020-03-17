package io.gitlab.donespeak.springbootsamples.swagger2.swagger2;

import io.gitlab.donespeak.springbootsamples.swagger2.swagger2.SwaggerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 16:48
 */
@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger2", name = "enable", havingValue = "true")
public class Swagger2Config {

	@Autowired
	private SwaggerProperties swaggerProperties;

	/**
	 * 配置Swagger2
	 */
	@Bean
	public Docket swaggerRestAPi() {
		//@formatter:off
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
			.select()
				// .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class)).paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
			.build();
		//@formatter:on
	}

	/**
	 * Api描述信息
	 */
	private ApiInfo apiInfo() {
		SwaggerProperties.ApiInfo apiInfoProperties = swaggerProperties.getApiInfo();
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder()
			.title(apiInfoProperties.getTitle())
			.description(apiInfoProperties.getDescription())
			.version(apiInfoProperties.getVersion());

		if(apiInfoProperties.getContact() != null) {
			SwaggerProperties.Contact contactProperties = apiInfoProperties.getContact();
			apiInfoBuilder.contact(
				new Contact(contactProperties.getName(), contactProperties.getUrl(), contactProperties.getEmail()));
		}

		return apiInfoBuilder.build();
	}

	@PostConstruct
	public void postConstruct() {
		log.info("Swagger2 is enable. You can visit: /swagger-ui.html");
	}
}
