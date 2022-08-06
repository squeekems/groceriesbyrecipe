package com.jackrkern.groceriesbyrecipe.security;

import static org.springframework.util.StringUtils.capitalize;
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/* @author "Jack Kern" */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
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

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.anonymous().principal(capitalize(strMapping(USER))).authorities(GUEST_ROLE).and().authorizeRequests()
			.antMatchers(	ITEMS,							//
							ITEMS + ALL,					//
							ITEMS + EDIT,					//
							ITEMS + ADD,					//
							ITEMS + REMOVE, 				//
							ITEMS + GET + ITEM,				//
							RECIPES,						//
							LIST,							//
							SETTINGS)						//
			.authenticated()								//
			.anyRequest().permitAll()						//
			.and()											//
			.formLogin()									//
			.loginPage(LOGIN)								//
			.usernameParameter(strMapping(EMAIL))			//
			.defaultSuccessUrl(ITEMS)						//
			.permitAll()									//
			.and()											//
			.logout().logoutSuccessUrl(INDEX).permitAll();	//
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception
	{
		return (web) -> web.ignoring().antMatchers();
	}
}