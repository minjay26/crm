package cn.tendata.crm.webapp.config;

import cn.tendata.crm.admin.web.bind.support.CurrentUserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Luo Min on 2016/11/4.
 */
@Configuration
@ComponentScan(basePackages = "cn.tendata.crm.**.web.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Bean
    public CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver() {
        return new CurrentUserHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver());

    }
}
