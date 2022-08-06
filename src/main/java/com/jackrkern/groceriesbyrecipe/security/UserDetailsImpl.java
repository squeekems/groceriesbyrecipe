/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.security;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
public class UserDetailsImpl implements UserDetails
{
	private final User user;

	public UserDetailsImpl(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{ return null; }

	@Override
	public String getPassword() {
		if (user != null)
			return user.getPassword();
		else
			return null;
	}

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

	public String getFullName() {
		return user.toString();
	}
}