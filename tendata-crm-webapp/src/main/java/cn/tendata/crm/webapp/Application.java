package cn.tendata.crm.webapp;


import cn.tendata.crm.service.EntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


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
	    static class JpaConfig {}
	    
	    @Configuration
	    @ComponentScan(basePackageClasses={EntityService.class})
	    static class ServiceConfig {}
}
