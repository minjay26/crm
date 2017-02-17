package cn.tendata.crm.webapp;


import cn.tendata.crm.service.EntityService;
import com.mongodb.Mongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.UnknownHostException;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaAuditing
    @EnableJpaRepositories(basePackages = "cn.tendata.crm.data.repository")
    @EntityScan(basePackages = "cn.tendata.crm.data.domain")
    static class JpaConfig {
    }

    @Configuration
    @ComponentScan(basePackageClasses = {EntityService.class})
    static class ServiceConfig {
    }




}
