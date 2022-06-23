package com.jackrkern.groceriesbyrecipe;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UnitOfMeasurementRepositoryTests
{
	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindAllByUser()
	{
		Long userID = (long) 1;

		User user = userRepository.findById(userID).get();

		List<UnitOfMeasurement> unitOfMeasurements = unitOfMeasurementRepository.findAllByUser(user);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		unitOfMeasurements.forEach(System.out::println);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertThat(unitOfMeasurements).isNotNull();
	}
}