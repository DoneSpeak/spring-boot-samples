package io.gitlab.donespeak.springbootsamples.swagger2.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 17:35
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties {

	private ApiInfo apiInfo;

	@Data
	public static class ApiInfo {
		private boolean enable;
		private String version;
		private String title;
		private String description;
		private String termsOfServiceUrl;
		private String license;
		private String licenseUrl;
		private Contact contact;
	}

	@Data
	public static class Contact {
		private String name;
		private String url;
		private String email;		
	}
}