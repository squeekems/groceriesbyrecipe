/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.controllers;

/**
 * @imports
 */
import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/**
 *  @author Jack Kern <jackrkern@gmail.com>
 */
@Controller
@RequestMapping(SETTINGS)
public class SettingsController {

	Logger logger = LoggerFactory.getLogger(SettingsController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String getSettings(Model model) {
		model.addAttribute(
				pluralOf(demap(AISLE)), itemService.getAisles((User) userService.getPrincipal())
		);
		model.addAttribute(demap(AISLE), new Aisle());
		model.addAttribute(
				demap(UNITS_OF_MEASUREMENT),
				recipeService.getUnitsOfMeasurement((User) userService.getPrincipal())
		);
		model.addAttribute(demap(UNIT_OF_MEASUREMENT), new UnitOfMeasurement());
		model.addAttribute(
				pluralOf(demap(AMOUNT)), recipeService.getAmounts((User) userService.getPrincipal())
		);
		model.addAttribute(demap(AMOUNT), new Amount());
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(GET)),
				demap(SETTINGS)
		}) + PERIOD);
		return demap(SETTINGS);
	}

	// Create
	@PostMapping(ADD + AISLE)
	public RedirectView addAisle(
			Aisle aisle,
			RedirectAttributes redirectAttributes) {
		aisle.setUser((User) userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(demap(AISLE)),
						aisle,
						capitalize(pastOf(demap(ADD)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				demap(AISLE),
				pastOf(CALL),
				aisle.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Create
	@PostMapping(ADD + UNIT_OF_MEASUREMENT)
	public RedirectView addUnitOfMeasurement(
			UnitOfMeasurement unitOfMeasurement,
			RedirectAttributes redirectAttributes) {
		unitOfMeasurement.setUser((User) userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(space(demap(UNIT_OF_MEASUREMENT))),
						unitOfMeasurement,
						capitalize(pastOf(demap(ADD)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				demap(UNIT_OF_MEASUREMENT),
				pastOf(CALL),
				unitOfMeasurement.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Create
	@PostMapping(ADD + AMOUNT)
	public RedirectView addAmount(
			Amount amount,
			RedirectAttributes redirectAttributes) {
		amount.setUser((User) userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(demap(AMOUNT)),
						amount,
						capitalize(pastOf(demap(ADD)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(ADD)),
				demap(AMOUNT),
				pastOf(CALL),
				amount.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Delete
	@GetMapping(REMOVE + AISLE + PVM_AISLE_ID)
	public RedirectView removeAisle(
			@PathVariable(value = PVV_AISLE_ID) Long aisleID,
			RedirectAttributes redirectAttributes) {
		// create aisle object to use in feedback
		Aisle aisle = itemService.getAisleByID(aisleID);
		itemService.deleteAisle(aisleID);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(demap(AISLE)),
						aisle,
						capitalize(pastOf(demap(REMOVE)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(DELETE)),
				demap(AISLE),
				pastOf(CALL),
				aisle.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Delete
	@GetMapping(REMOVE + UNIT_OF_MEASUREMENT + PVM_UNIT_OF_MEASUREMENT_ID)
	public RedirectView removeUnitOfMeasurement(
			@PathVariable(value = PVV_UNIT_OF_MEASUREMENT_ID) Long unitOfMeasurementID,
			RedirectAttributes redirectAttributes) {
		// create unitOfMeasurement object to use in feedback
		UnitOfMeasurement unitOfMeasurement = recipeService.getUnitOfMeasurementByID(
				unitOfMeasurementID
		);
		recipeService.deleteUnitOfMeasurement(unitOfMeasurementID);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(space(demap(UNIT_OF_MEASUREMENT))),
						unitOfMeasurement,
						capitalize(pastOf(demap(REMOVE)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(DELETE)),
				demap(UNIT_OF_MEASUREMENT),
				pastOf(CALL),
				unitOfMeasurement.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Delete
	@GetMapping(REMOVE + AMOUNT + PVM_AMOUNT_ID)
	public RedirectView removeAmount(
			@PathVariable(value = PVV_AMOUNT_ID) Long amountID,
			RedirectAttributes redirectAttributes) {
		// create amount object to use in feedback
		Amount amount = recipeService.getAmountByID(amountID);
		recipeService.deleteAmount(amountID);
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(demap(AMOUNT)),
						amount,
						capitalize(pastOf(demap(REMOVE)))
				)
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(DELETE)),
				demap(AMOUNT),
				pastOf(CALL),
				amount.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Delete
	@GetMapping(REMOVE + ACCOUNT)
	public RedirectView removeAccount(RedirectAttributes redirectAttributes) {
		User user = (User) userService.getPrincipal();
		userService.deleteAccount(user.getUserID());
		redirectAttributes.addFlashAttribute(
				SUCCESS,
				String.format(
						NOUN_Q_NOUN_Q_VERB,
						capitalize(demap(ACCOUNT)),
						user,
						capitalize(pastOf(demap(REMOVE)))
				)
		);
		logger.info(space(new String[]{
				user.toString(),
				pastOf(demap(DELETE)),
				demap(ACCOUNT),
				pastOf(CALL),
				user.toString()
		}) + PERIOD);
		SecurityContextHolder.getContext().setAuthentication(null);
		return new RedirectView(INDEX);
	}

	// Update
	@RequestMapping(value = EDIT + AISLE, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAisle(
			Aisle aisle,
			RedirectAttributes redirectAttributes) {
		aisle.setUser((User) userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, aisle, capitalize(pastOf(demap(EDIT))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(UPDATE)),
				demap(AISLE),
				pastOf(CALL),
				aisle.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Update
	@RequestMapping(
			value = EDIT + UNIT_OF_MEASUREMENT,
			method = { RequestMethod.PUT, RequestMethod.GET }
	)
	public RedirectView updateUnitOfMeasurement(
			UnitOfMeasurement unitOfMeasurement,
			RedirectAttributes redirectAttributes) {
		unitOfMeasurement.setUser((User) userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, unitOfMeasurement, capitalize(pastOf(demap(EDIT))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(UPDATE)),
				demap(UNIT_OF_MEASUREMENT),
				pastOf(CALL),
				unitOfMeasurement.toString()
		}) + PERIOD);
		return new RedirectView(SETTINGS);
	}

	// Update
	@RequestMapping(value = EDIT + AMOUNT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAmount(
			Amount amount,
			RedirectAttributes redirectAttributes) {
		amount.setUser((User) userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(
				SUCCESS, String.format(S_S, amount, capitalize(pastOf(demap(EDIT))))
		);
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(UPDATE)),
				demap(AMOUNT),
				pastOf(CALL),
				amount.toString()
		}) + PERIOD);
		return new RedirectView(INDEX);
	}
}