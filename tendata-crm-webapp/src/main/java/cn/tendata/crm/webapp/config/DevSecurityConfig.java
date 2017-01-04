package cn.tendata.crm.webapp.config;


import cn.tendata.crm.admin.web.util.SecurityAccess;
import cn.tendata.crm.service.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;


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
				.csrf().disable()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .and()
        .headers()
            .frameOptions()
            .sameOrigin();
	}

//	@Bean
//	public AffirmativeBased accessDecisionManager() {
//		List<WebExpressionVoter> list=new ArrayList<>();
//		list.add(webExpressionVoter());
//		AffirmativeBased affirmativeBased = new AffirmativeBased(list);
//		return affirmativeBased;
//	}

//	@Bean
//	public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
//		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//		defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
//		return defaultWebSecurityExpressionHandler;
//	}
//
//	@Bean
//	public RoleHierarchyImpl roleHierarchy() {
//		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//		String roleHierarchyStringRepresentation = SecurityAccess.PERMISSION_ALL + ">" + SecurityAccess.PERMISSION_ADMIN_VIEW;
//		roleHierarchy.setHierarchy(roleHierarchyStringRepresentation);
//		return roleHierarchy;
//	}
//
////	@Bean
////	public RoleHierarchyVoter roleVoter() {
////		return new RoleHierarchyVoter(roleHierarchy());
////	}
//
//	@Bean
//	public WebExpressionVoter webExpressionVoter() {
//		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//		webExpressionVoter.setExpressionHandler(defaultWebSecurityExpressionHandler());
//		return webExpressionVoter;
//	}


	

}
