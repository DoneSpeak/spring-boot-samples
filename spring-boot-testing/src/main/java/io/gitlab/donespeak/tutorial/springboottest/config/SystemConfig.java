package io.gitlab.donespeak.tutorial.springboottest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yang Guanrong
 * @date 2020/02/29 12:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "bees360.com.system-config")
public class SystemConfig {
    /**
     * 完整前端的域名（带端口号）
     */
    private String webClientAddress;

    /**
     * 后端服务器域名（含端口号和context-path)
     */
    private String webServer;

    //下载文件服务的域名,目前是http://api.bees360.io,后期可以是asw s3的域名,也可以是其它文件服务的可访问域名
    /**
     * 资源服务区域名（如图片服务器域名）
     */
    private String resourceServer;
}