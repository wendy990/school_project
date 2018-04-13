/*package com.iktpreobuka.elektronski_dnevnik.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.passwordEncoder(passwordEncoder()).dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	
	 /*@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/public/**").permitAll() 
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()
	                .and()
	            .logout()                                    
	                .permitAll();
	    }*/

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/public/**").permitAll().anyRequest().authenticated().and().formLogin() // login
																														// configuration
				.loginPage("/public/login").loginProcessingUrl("/login").usernameParameter("app_username")
				.passwordParameter("app_password").defaultSuccessUrl("/login-after").and().logout() // logout
																									// configuration
				.logoutUrl("/app-logout").logoutSuccessUrl("/app/login").and().exceptionHandling() // exception handling
																									// configuration
				.accessDeniedPage("/app/error");
	}*/

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable() .authorizeRequests()
	 * .antMatchers("/public/**").permitAll() .anyRequest().authenticated() .and()
	 * .formLogin() .loginPage("/public/login") .loginProcessingUrl("/login")
	 * .defaultSuccessUrl("/login-after") .failureUrl("/public/login?error1=true")
	 * .and().httpBasic() .authenticationEntryPoint(authEntryPoint); }
	 */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests() .antMatchers("/", "/home").permitAll()
	 * .antMatchers("/admin/**","/newuser").access("hasRole('ADMIN')")
	 * .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	 * .and().formLogin().loginPage("/login")
	 * .usernameParameter("ssoId").passwordParameter("password") .and().csrf()
	 * .and().exceptionHandling().accessDeniedPage("/Access_Denied"); }
	 */


