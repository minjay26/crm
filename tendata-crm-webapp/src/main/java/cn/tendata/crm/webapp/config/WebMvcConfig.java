package cn.tendata.crm.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Luo Min on 2016/11/4.
 */
@Configuration
@ComponentScan(basePackages = "cn.tendata.crm.**.web.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter{
}
