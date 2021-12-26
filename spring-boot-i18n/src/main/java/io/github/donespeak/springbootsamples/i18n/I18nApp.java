package io.github.donespeak.springbootsamples.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@SpringBootApplication
public class I18nApp {

    public static void main(String[] args) {
        SpringApplication.run(I18nApp.class);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "spring.mvc", name = "customer-locale-resolver", havingValue = "cookie")
    public class MvcConfigurer implements WebMvcConfigurer {

        @Bean
        public LocaleResolver localeResolver(@Value("${spring.mvc.locale:null}") Locale locale) {
            CookieLocaleResolver resolver = new CookieLocaleResolver();
            if (locale != null) {
                resolver.setDefaultLocale(locale);
            }
            return resolver;
        }
        @Bean
        public LocaleChangeInterceptor localeInterceptor() {
            LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
            localeInterceptor.setParamName("lang");
            return localeInterceptor;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeInterceptor());
        }
    }

}
