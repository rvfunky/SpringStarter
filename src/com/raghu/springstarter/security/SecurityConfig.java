package com.raghu.springstarter.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.raghu.springstarter.dao.UserDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private UserDAO userDAO;
	
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
 
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler
      authenticationSuccessHandler;
	
	@Autowired
  public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception
  {
    auth
      .userDetailsService(userDAO);
      //.passwordEncoder(bcryptEncoder);
        
  }

	protected void configure(HttpSecurity http) throws Exception {
    http
    	.csrf().disable()
    	.exceptionHandling()
    	.authenticationEntryPoint(restAuthenticationEntryPoint)
    	.and()
        .authorizeRequests()
          .antMatchers("/").permitAll()
          .antMatchers("/resources/**").permitAll()
          .antMatchers("/admin**/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
        .formLogin()
        .successHandler(authenticationSuccessHandler)
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
          .loginPage("/")
            .and()
        .logout();
	}
	
    @Bean
    public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler(){
        return new MySavedRequestAwareAuthenticationSuccessHandler();
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
