/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.business;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.repositories.AisleRepository;
import com.jackrkern.groceriesbyrecipe.repositories.AmountRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UnitOfMeasurementRepository;
import com.jackrkern.groceriesbyrecipe.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

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
		logger.info(space(new String[]{
				user.toDetailedString(),
				capitalize(pastOf(demap(SAVE)))
		}) + PERIOD);
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
			try {
				logger.info(space(new String[]{
						amount.toDetailedString(),
						capitalize(pastOf(demap(DELETE)))
				}) + PERIOD);
				amountRepository.delete(amount);
			} catch (IllegalArgumentException e) {
				logger.warn(space( new String[]{
						(demap(DELETE) + capitalize(demap(ACCOUNT))),
						pastOf(CATCH),
						e.getClass().getName()
				}), e);
			}
		}
		for (Aisle aisle : aisleRepository.findAllByUser(user)) {
			try {
				logger.info(space(new String[]{
						aisle.toDetailedString(),
						capitalize(pastOf(demap(DELETE)))
				}) + PERIOD);
				aisleRepository.delete(aisle);
			} catch (IllegalArgumentException e) {
				logger.warn(space( new String[]{
						(demap(DELETE) + capitalize(demap(ACCOUNT))),
						pastOf(CATCH),
						e.getClass().getName()
				}), e);
			}
		}
		for (UnitOfMeasurement unitOfMeasurement : unitOfMeasurementRepository.findAllByUser(user)) {
			try {
				logger.info(space(new String[]{
						unitOfMeasurement.toDetailedString(),
						capitalize(pastOf(demap(DELETE)))
				}) + PERIOD);
				unitOfMeasurementRepository.delete(unitOfMeasurement);
			} catch (IllegalArgumentException e) {
				logger.warn(space( new String[]{
						(demap(DELETE) + capitalize(demap(ACCOUNT))),
						pastOf(CATCH),
						e.getClass().getName()
				}), e);
			}
		}
		try {
			logger.info(space(new String[]{
					userRepository.findById(userID).orElseThrow().toDetailedString(),
					capitalize(pastOf(demap(DELETE)))
			}) + PERIOD);
			userRepository.deleteById(userID);
		} catch (IllegalArgumentException e) {
			logger.warn(space( new String[]{
					(demap(DELETE) + capitalize(demap(ACCOUNT))),
					pastOf(CATCH),
					e.getClass().getName()
			}), e);
		}
	}
}