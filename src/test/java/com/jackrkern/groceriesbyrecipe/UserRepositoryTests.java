package com.jackrkern.groceriesbyrecipe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateUser()
	{
		User user = new User();
		user.setEmail("rachel.wilk2413@gmail.com");
		user.setPassword("password");
		user.setUsername("Tinkerthorn");
		user.setFirstName("Rachel");
		user.setLastName("Wilk");

		User savedUser = userRepository.save(user);

		User existUser = entityManager.find(User.class, savedUser.getUserID());

		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}

	@Test
	public void testFindUserByEmail()
	{
		String email = "jackrkern@gmail.com";

		User user = userRepository.findByEmail(email);

		assertThat(user).isNotNull();
	}
}
