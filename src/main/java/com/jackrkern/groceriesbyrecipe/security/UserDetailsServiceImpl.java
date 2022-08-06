package com.jackrkern.groceriesbyrecipe.security;

import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/* @author "Jack Kern" */
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email)
	{
		User user = userRepository.findByEmail(email);
		return new UserDetailsImpl(user);
	}
}