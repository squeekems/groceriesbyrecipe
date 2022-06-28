package com.jackrkern.groceriesbyrecipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class UserServiceTests
{
	@Autowired
	private UserService userService;

	@Test
	public void testGetByEmail()
	{
		String email = "jackrkern@gmail.com";

		User user = userService.getByEmail(email);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(user);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(user).isNotNull();
	}

	@Test
	public void testRegisterUser()
	{
		User user = new User();
		user.setEmail("Test@api.jupiter.junit.org");
		user.setPassword("password");

		userService.registerUser(user);

		User existUser = userService.getByEmail(user.getEmail());

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(existUser.getEmail() + " = " + user.getEmail());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
}
