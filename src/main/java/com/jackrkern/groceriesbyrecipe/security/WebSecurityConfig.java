package com.jackrkern.groceriesbyrecipe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* @author "Jack Kern" */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.anonymous().principal("User").authorities("GUEST_ROLE").and()
			.authorizeRequests()							// 
			.antMatchers(	"/items",						// 
			             	"/items/all",					// 
			             	"/items/edit",					// 
			             	"/items/add",					// 
			             	"/items/remove", 				// 
			             	"/items/getItemByID",			// 
							"/recipes",						// 
							"/list").authenticated()		// 
			.anyRequest().permitAll()						// 
			.and()											// 
			.formLogin()									// 
			.loginPage("/login")							// 
				.usernameParameter("email")					// 
				.defaultSuccessUrl("/items")				// 
				.permitAll()								// 
			.and()											// 
			.logout().logoutSuccessUrl("/").permitAll();	// 
	}
}