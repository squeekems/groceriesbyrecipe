package com.jackrkern.groceriesbyrecipe.security;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@SuppressWarnings(SERIAL)
public class UserDetailsImpl implements UserDetails
{
	private User user;

	public UserDetailsImpl(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{ return null; }

	@Override
	public String getPassword()
	{ return user.getPassword(); }

	@Override
	public String getUsername()
	{ return user.getEmail(); }

	@Override
	public boolean isAccountNonExpired()
	{ return true; }

	@Override
	public boolean isAccountNonLocked()
	{ return true; }

	@Override
	public boolean isCredentialsNonExpired()
	{ return true; }

	@Override
	public boolean isEnabled()
	{ return true; }

	public String getFullName()
	{
		String fullName;
		if (user.getFirstName() != null && user.getFirstName() != "" && !user.getFirstName().isEmpty())
		{
			fullName = user.getFirstName();
			if (user.getLastName() != null && user.getLastName() != "" && !user.getLastName().isEmpty())
				fullName += " " + user.getLastName();
		} else if (user.getLastName() != null && user.getLastName() != "" && !user.getLastName().isEmpty())
			fullName = user.getLastName();
		else if (user.getUsername() != null && user.getUsername() != "" && !user.getUsername().isEmpty())
			fullName = user.getUsername();
		else
			fullName = user.getEmail();
		return fullName;
	}
}