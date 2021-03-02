package io.github.donespeak.springbootsamples.intercept.config;

import io.github.donespeak.springbootsamples.intercept.intercept.HttpRequestResponseLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author DoneSpeak
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(InterceptorRegister.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new HttpRequestResponseLoggingInterceptor()).addPathPatterns("/**");
    }
}