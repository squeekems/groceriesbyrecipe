package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
import static java.lang.System.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.time.format.DateTimeFormatter;

/* @author "Jack Kern" */

@Controller
public class SettingsController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private ItemService itemService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

	@GetMapping(SETTINGS)
	public String getSettings(Model model)
	{
		model.addAttribute(strPlural(strMapping(AISLE)), itemService.getAisles(userService.getPrincipal()));
		model.addAttribute(strMapping(AISLE), new Aisle());
		model.addAttribute(	strMapping(UNITSOFMEASUREMENT),
							recipeService.getUnitsOfMeasurement(userService.getPrincipal()));
		model.addAttribute(strMapping(UNITOFMEASUREMENT), new UnitOfMeasurement());
		model.addAttribute(strPlural(strMapping(AMOUNT)), recipeService.getAmounts(userService.getPrincipal()));
		model.addAttribute(strMapping(AMOUNT), new Amount());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(SETTINGS));
		return strMapping(SETTINGS);
	}

	// Create
	@PostMapping(SETTINGS + ADD + AISLE)
	public RedirectView addAisle(Aisle aisle, RedirectAttributes redirectAttributes)
	{
		aisle.setUser(userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, capitalize(strMapping(AISLE)),
																	aisle, capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(AISLE), aisle);
		return new RedirectView(SETTINGS);
	}

	// Create
	@PostMapping(SETTINGS + ADD + UNITOFMEASUREMENT)
	public RedirectView addUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement, RedirectAttributes redirectAttributes)
	{
		unitOfMeasurement.setUser(userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(	SUCCESS,
												String.format(	NOUNqNOUNqsVERBED,
																capitalize(strSpace(strMapping(UNITOFMEASUREMENT))),
																unitOfMeasurement,
																capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strSpace(strMapping(UNITOFMEASUREMENT)), unitOfMeasurement);
		return new RedirectView(SETTINGS);
	}

	// Create
	@PostMapping(SETTINGS + ADD + AMOUNT)
	public RedirectView addAmount(Amount amount, RedirectAttributes redirectAttributes)
	{
		amount.setUser(userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, capitalize(strMapping(AMOUNT)),
																	amount, capitalize(strPast(strMapping(ADD)))));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(AMOUNT), amount);
		return new RedirectView(SETTINGS);
	}

	// Create
	@GetMapping(SETTINGS + REMOVE + AISLE + PVMAISLEID)
	public RedirectView removeAisle(@PathVariable(value = PVVAISLEID)
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		// create aisle object to use in feedback
		Aisle aisle = itemService.getAisleByID(aisleID);
		itemService.deleteAisle(aisleID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, capitalize(strMapping(AISLE)),
																	aisle, capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(DELETE)), strMapping(AISLE), aisle);
		return new RedirectView(SETTINGS);
	}

	// Create
	@GetMapping(SETTINGS + REMOVE + UNITOFMEASUREMENT + PVMUNITOFMEASUREMENTID)
	public RedirectView removeUnitOfMeasurement(@PathVariable(value = PVVUNITOFMEASUREMENTID)
	Long unitOfMeasurementID, RedirectAttributes redirectAttributes)
	{
		// create unitOfMeasurement object to use in feedback
		UnitOfMeasurement unitOfMeasurement = recipeService.getUnitOfMeasurementByID(unitOfMeasurementID);
		recipeService.deleteUnitOfMeasurement(unitOfMeasurementID);
		redirectAttributes.addFlashAttribute(	SUCCESS,
												String.format(	NOUNqNOUNqsVERBED,
																capitalize(strSpace(strMapping(UNITOFMEASUREMENT))),
																unitOfMeasurement,
																capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(DELETE)), strSpace(strMapping(UNITOFMEASUREMENT)), unitOfMeasurement);
		return new RedirectView(SETTINGS);
	}

	// Create
	@GetMapping(SETTINGS + REMOVE + AMOUNT + PVMAMOUNTID)
	public RedirectView removeAmount(@PathVariable(value = PVVAMOUNTID)
	Long amountID, RedirectAttributes redirectAttributes)
	{
		// create amount object to use in feedback
		Amount amount = recipeService.getAmountByID(amountID);
		recipeService.deleteAmount(amountID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, capitalize(strMapping(AMOUNT)),
																	amount, capitalize(strPast(strMapping(REMOVE)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(DELETE)), strMapping(AMOUNT), amount);
		return new RedirectView(SETTINGS);
	}

	// Update
	@RequestMapping(value = SETTINGS + EDIT + AISLE, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAisle(Aisle aisle, RedirectAttributes redirectAttributes)
	{
		aisle.setUser(userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, aisle,
																	capitalize(strPast(strMapping(EDIT)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(UPDATE)), strMapping(AISLE), aisle);
		return new RedirectView(SETTINGS);
	}

	// Update
	@RequestMapping(value = SETTINGS + EDIT + UNITOFMEASUREMENT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement,
												RedirectAttributes redirectAttributes)
	{
		unitOfMeasurement.setUser(userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, unitOfMeasurement,
																	capitalize(strPast(strMapping(EDIT)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(UPDATE)), strMapping(UNITOFMEASUREMENT), unitOfMeasurement);
		return new RedirectView(SETTINGS);
	}

	// Update
	@RequestMapping(value = SETTINGS + EDIT + AMOUNT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAmount(Amount amount, RedirectAttributes redirectAttributes)
	{
		amount.setUser(userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, amount,
																	capitalize(strPast(strMapping(EDIT)))));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strPast(strMapping(UPDATE)), strMapping(AMOUNT), amount);
		return new RedirectView(SETTINGS);
	}
}