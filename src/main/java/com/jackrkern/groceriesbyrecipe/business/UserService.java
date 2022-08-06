package com.jackrkern.groceriesbyrecipe.business;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;
import static org.springframework.util.StringUtils.capitalize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;

/* @author "Jack Kern" */

@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private AisleRepository aisleRepository;

	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	public User getPrincipal()
	{ return getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()); }

	public User registerUser(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user = userRepository.save(user);
		out.printf(STRINGsSTRINGnl, user.toDetailedString(), capitalize(strPast(strMapping(SAVE))));
		WelcomePackage(user);
		return user;
	}

	public User getByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	public void WelcomePackage(User user)
	{
		Amount amount0 = new Amount();
		amount0.setUser(user);
		amount0.setValue(ONEfsFOUR);
		amountRepository.save(amount0);
		Amount amount1 = new Amount();
		amount1.setUser(user);
		amount1.setValue(ONEfsTHREE);
		amountRepository.save(amount1);
		Amount amount2 = new Amount();
		amount2.setUser(user);
		amount2.setValue(ONEfsTWO);
		amountRepository.save(amount2);
		Amount amount3 = new Amount();
		amount3.setUser(user);
		amount3.setValue(TWOfsTHREE);
		amountRepository.save(amount3);
		Amount amount4 = new Amount();
		amount4.setUser(user);
		amount4.setValue(THREEfsFOUR);
		amountRepository.save(amount4);
		for (int i = 1; i < 25; i++)
		{
			Amount amount = new Amount();
			amount.setUser(user);
			amount.setValue(Integer.toString(i));
			amountRepository.save(amount);
		}
		for (int i = 0; i < 21; i++)
		{
			Aisle aisle = new Aisle();
			aisle.setUser(user);
			aisle.setName(String.format(DEFAULTAISLEFORMAT, i));
			aisleRepository.save(aisle);
		}
		UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
		unitOfMeasurement.setUser(user);
		unitOfMeasurement.setName(EMPTYUNITOFMEASUREMENT);
		unitOfMeasurementRepository.save(unitOfMeasurement);
		UnitOfMeasurement unitOfMeasurement0 = new UnitOfMeasurement();
		unitOfMeasurement0.setUser(user);
		unitOfMeasurement0.setName(TSP);
		unitOfMeasurementRepository.save(unitOfMeasurement0);
		UnitOfMeasurement unitOfMeasurement1 = new UnitOfMeasurement();
		unitOfMeasurement1.setUser(user);
		unitOfMeasurement1.setName(TBS);
		unitOfMeasurementRepository.save(unitOfMeasurement1);
		UnitOfMeasurement unitOfMeasurement2 = new UnitOfMeasurement();
		unitOfMeasurement2.setUser(user);
		unitOfMeasurement2.setName(OUNCE);
		unitOfMeasurementRepository.save(unitOfMeasurement2);
		UnitOfMeasurement unitOfMeasurement3 = new UnitOfMeasurement();
		unitOfMeasurement3.setUser(user);
		unitOfMeasurement3.setName(POUND);
		unitOfMeasurementRepository.save(unitOfMeasurement3);
		UnitOfMeasurement unitOfMeasurement4 = new UnitOfMeasurement();
		unitOfMeasurement4.setUser(user);
		unitOfMeasurement4.setName(CUP);
		unitOfMeasurementRepository.save(unitOfMeasurement4);
		UnitOfMeasurement unitOfMeasurement5 = new UnitOfMeasurement();
		unitOfMeasurement5.setUser(user);
		unitOfMeasurement5.setName(SLICE);
		unitOfMeasurementRepository.save(unitOfMeasurement5);
	}
}