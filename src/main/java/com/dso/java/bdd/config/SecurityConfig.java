package com.dso.java.bdd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * public void configureGlobal(final AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("usernmae").password("password").roles("ADMIN");
	 * }
	 */

	/**
	 * If you’ve enabled Spring Security in your Spring Boot application, you will
	 * not be able to access the H2 database console. With its default settings
	 * under Spring Boot, Spring Security will block access to H2 database console.
	 * 
	 * To enable access to the H2 database console under Spring Security you need to
	 * change three things:
	 * 
	 * Allow all access to the url path /console/*. Disable CRSF (Cross-Site Request
	 * Forgery). By default, Spring Security will protect against CRSF attacks.
	 * Since the H2 database console runs inside a frame, you need to enable this in
	 * in Spring Security. The following Spring Security Configuration will:
	 * 
	 * Allow all requests to the root url (“/”) Allow all requests to the H2
	 * database console url (“/console/*”) Disable CSRF protection Disable
	 * X-Frame-Options in Spring Security
	 * 
	 */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
				.antMatchers("/console/**").permitAll();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}

}