package io.gitlab.donespeak.tutorial.springboottest.mapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yang Guanrong
 * @date 2020/02/24 21:51
 */
@Configuration
@MapperScan(basePackageClasses = {UserMapper.class})
public class MyBatisConfig {
}
