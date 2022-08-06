package com.jackrkern.groceriesbyrecipe.business;

import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;
import static java.lang.System.out;

/* @author "Jack Kern" */

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private AisleRepository aisleRepository;

	@Autowired
	private UnitOfMeasurementRepository unitOfMeasurementRepository;

	public Object getPrincipal() {
		User user = getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (user != null) {
			return user;
		} else {
			return capitalize(SOMEONE);
		}
	}

	public User registerUser(User user) {
		user = userRepository.save(user);
		WelcomePackage(user);
		out.printf(S_S_NL, user.toDetailedString(), capitalize(pastOf(demap(SAVE))));
		return user;
	}

	public User getByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	public void WelcomePackage(User user) {
		Amount amount0 = new Amount();
		amount0.setUser(user);
		amount0.setValue(ONE_FOURTH);
		amountRepository.save(amount0);
		Amount amount1 = new Amount();
		amount1.setUser(user);
		amount1.setValue(ONE_THIRD);
		amountRepository.save(amount1);
		Amount amount2 = new Amount();
		amount2.setUser(user);
		amount2.setValue(ONE_HALF);
		amountRepository.save(amount2);
		Amount amount3 = new Amount();
		amount3.setUser(user);
		amount3.setValue(TWO_THIRDS);
		amountRepository.save(amount3);
		Amount amount4 = new Amount();
		amount4.setUser(user);
		amount4.setValue(THREE_FOURTHS);
		amountRepository.save(amount4);
		for (int i = 1; i < 25; i++) {
			Amount amount = new Amount();
			amount.setUser(user);
			amount.setValue(Integer.toString(i));
			amountRepository.save(amount);
		}
		for (int i = 0; i < 21; i++) {
			Aisle aisle = new Aisle();
			aisle.setUser(user);
			aisle.setName(String.format(DEFAULT_AISLE_FORMAT, i));
			aisleRepository.save(aisle);
		}
		UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
		unitOfMeasurement.setUser(user);
		unitOfMeasurement.setName(EMPTY_UNIT_OF_MEASUREMENT);
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

	public void deleteAccount(Long userID) {
		User user = userRepository.findById(userID).orElseThrow();
		for (Amount amount : amountRepository.findAllByUser(user)) {
			amountRepository.delete(amount);
		}
		for (Aisle aisle : aisleRepository.findAllByUser(user)) {
			aisleRepository.delete(aisle);
		}
		for (UnitOfMeasurement unitOfMeasurement : unitOfMeasurementRepository.findAllByUser(user)) {
			unitOfMeasurementRepository.delete(unitOfMeasurement);
		}
		userRepository.deleteById(userID);
	}
}