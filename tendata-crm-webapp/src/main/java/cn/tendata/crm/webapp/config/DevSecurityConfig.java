package cn.tendata.crm.webapp.config;


import cn.tendata.crm.service.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Profile("dev")
@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Order(2)
public class DevSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public LoginUserService loginUserService(){
		return  new LoginUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/admin/**").authenticated()
            .anyRequest().permitAll()
            .and()
        .formLogin()
            .permitAll()
            .and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
        .headers()
            .frameOptions()
            .sameOrigin();
	}
	

}
