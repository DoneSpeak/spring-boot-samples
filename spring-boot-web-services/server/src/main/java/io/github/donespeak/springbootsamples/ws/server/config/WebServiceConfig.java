package io.github.donespeak.springbootsamples.ws.server.config;

import io.github.donespeak.springbootsamples.ws.server.core.SchemaConst;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * <p>
 * You need to specify bean names for MessageDispatcherServlet and DefaultWsdl11Definition.
 * Bean names determine the URL under which the web service and the generated WSDL file are available.
 * In this case, the WSDL will be available under http://<host>:<port>/ws/countries.wsdl.
 * </p>
 * TODO wsdl 路径是如何决定的？
 * @author Yang Guanrong
 * @date 2020/03/06 10:34
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * 需要设置 ApplicationContext 才能使得 Spring WS 自动检测 Spring beans。
     * 该messageDispatcherServlet 不会替换 Spring Boot 默认的DispatcherServlet
     */
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    /**
     * 暴露 WSDL 1.1 标准. 这里定义了获取wsdl的路径：/ws/countries.wsdl
     * @param countriesSchema
     * @return
     */
    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(SchemaConst.NAMESPACE_URI);
        wsdl11Definition.setSchema(countriesSchema);

        return  wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/countries.xsd"));
    }

}
