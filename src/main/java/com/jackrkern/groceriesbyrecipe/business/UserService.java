package com.jackrkern.groceriesbyrecipe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public User getPrincipal()
	{ return getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()); }

	public void registerUser(User user)
	{
		if (user != null)
		{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRepository.save(user);
		} else
			throw new RuntimeException("User cannot be null");
	}

	public User getByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}
}