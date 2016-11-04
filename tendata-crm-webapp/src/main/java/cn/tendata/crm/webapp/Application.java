package cn.tendata.crm.webapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	    @Configuration
//	    @EnableTransactionManagement
//	    @EnableJpaAuditing
//	    @EnableJpaRepositories(basePackages = "cn.tendata.wenda.data.repository")
//	    @EntityScan(basePackages = "cn.tendata.wenda.data.domain")
//	    static class JpaConfig {}
	    
//	    @Configuration
//	    @ComponentScan(basePackageClasses={EntityService.class})
//	    @Import(FileExportConfig.class)
//	    static class ServiceConfig {}
}
