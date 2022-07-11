package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
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

	@GetMapping(sSETTINGS)
	public String getSettings(Model model)
	{
		model.addAttribute(AISLES, itemService.getAisles(userService.getPrincipal()));
		model.addAttribute(AISLE, new Aisle());
		model.addAttribute(UNITSOFMEASUREMENT, recipeService.getUnitsOfMeasurement(userService.getPrincipal()));
		model.addAttribute(UNITOFMEASUREMENT, new UnitOfMeasurement());
		model.addAttribute(AMOUNTS, recipeService.getAmounts(userService.getPrincipal()));
		model.addAttribute(AMOUNT, new Amount());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, SETTINGS);
		return SETTINGS;
	}

	// Create
	@PostMapping(sSETTINGS + sADD + sAISLE)
	public RedirectView addAisle(Aisle aisle, RedirectAttributes redirectAttributes)
	{
		aisle.setUser(userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cAISLE, aisle, cADDED));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, AISLE, aisle);
		return new RedirectView(sSETTINGS);
	}

	// Create
	@PostMapping(sSETTINGS + sADD + "/unitOfMeasurement")
	public RedirectView addUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement, RedirectAttributes redirectAttributes)
	{
		unitOfMeasurement.setUser(userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cUNITsOFscMEASUREMENT,
																	unitOfMeasurement, cADDED));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UNITsOFsMEASUREMENT,
					unitOfMeasurement);
		return new RedirectView(sSETTINGS);
	}

	// Create
	@PostMapping(sSETTINGS + sADD + "/amount")
	public RedirectView addAmount(Amount amount, RedirectAttributes redirectAttributes)
	{
		amount.setUser(userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cAMOUNT, amount, cADDED));
		out.printf(	PERSONsCREATEDsAsNEWsNOUNsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, AMOUNT, amount);
		return new RedirectView(sSETTINGS);
	}

	// Create
	@GetMapping(sSETTINGS + sREMOVE + sAISLE + "/{aisleID}")
	public RedirectView removeAisle(@PathVariable(value = "aisleID")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		// create aisle object to use in feedback
		Aisle aisle = itemService.getAisleByID(aisleID);
		itemService.deleteAisle(aisleID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cAISLE, aisle, cREMOVED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, DELETED, AISLE, aisle);
		return new RedirectView(sSETTINGS);
	}

	// Create
	@GetMapping(sSETTINGS + sREMOVE + sUNITOFMEASUREMENT + "/{unitOfMeasurementID}")
	public RedirectView removeUnitOfMeasurement(@PathVariable(value = "unitOfMeasurementID")
	Long unitOfMeasurementID, RedirectAttributes redirectAttributes)
	{
		// create unitOfMeasurement object to use in feedback
		UnitOfMeasurement unitOfMeasurement = recipeService.getUnitOfMeasurementByID(unitOfMeasurementID);
		recipeService.deleteUnitOfMeasurement(unitOfMeasurementID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cUNITsOFscMEASUREMENT,
																	unitOfMeasurement, cREMOVED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, DELETED,
					UNITsOFsMEASUREMENT, unitOfMeasurement);
		return new RedirectView(sSETTINGS);
	}

	// Create
	@GetMapping(sSETTINGS + sREMOVE + sAMOUNT + "/{amountID}")
	public RedirectView removeAmount(@PathVariable(value = "amountID")
	Long amountID, RedirectAttributes redirectAttributes)
	{
		// create amount object to use in feedback
		Amount amount = recipeService.getAmountByID(amountID);
		recipeService.deleteAmount(amountID);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(NOUNqNOUNqsVERBED, cAMOUNT, amount, cREMOVED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, DELETED, AMOUNT,
					amount);
		return new RedirectView(sSETTINGS);
	}

	// Update
	@RequestMapping(value = sSETTINGS + sEDIT + sAISLE, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAisle(Aisle aisle, RedirectAttributes redirectAttributes)
	{
		aisle.setUser(userService.getPrincipal());
		itemService.saveAisle(aisle);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, aisle, cEDITED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UPDATED, AISLE, aisle);
		return new RedirectView(sSETTINGS);
	}

	// Update
	@RequestMapping(value = sSETTINGS + sEDIT + sUNITOFMEASUREMENT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement,
												RedirectAttributes redirectAttributes)
	{
		unitOfMeasurement.setUser(userService.getPrincipal());
		recipeService.saveUnitOfMeasurement(unitOfMeasurement);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, unitOfMeasurement, cEDITED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UPDATED,
					UNITOFMEASUREMENT, unitOfMeasurement);
		return new RedirectView(sSETTINGS);
	}

	// Update
	@RequestMapping(value = sSETTINGS + sEDIT + sAMOUNT, method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView updateAmount(Amount amount, RedirectAttributes redirectAttributes)
	{
		amount.setUser(userService.getPrincipal());
		recipeService.saveAmount(amount);
		redirectAttributes.addFlashAttribute(SUCCESS, String.format(STRINGsSTRING, amount, cEDITED));
		out.printf(	PERSONsVERBEDsANsOBJECTsCALLEDsNOUNnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, UPDATED, AMOUNT,
					amount);
		return new RedirectView(sSETTINGS);
	}

//	// Gets Aisle to be Editted
//	@RequestMapping(sSETTINGS + sGET + cAISLE + cBYID + "/{aisleID}")
//	@ResponseBody
//	public Aisle getAisleByID(@PathVariable(value = "aisleID")
//	Long aisleID)
//	{
//		return itemService.getAisleByID(aisleID);
//	}
//
//	// Gets UnitOfMeasurement to be Editted
//	@RequestMapping(sSETTINGS + sGET + cUNITOFMEASUREMENT + cBYID + "/{unitOfMeasurementID}")
//	@ResponseBody
//	public UnitOfMeasurement getUnitOfMeasurementByID(@PathVariable(value = "unitOfMeasurementID")
//	Long unitOfMeasurementID)
//	{
//		return recipeService.getUnitOfMeasurementByID(unitOfMeasurementID);
//	}
//
//	// Gets Amount to be Editted
//	@RequestMapping(sSETTINGS + sGET + cAMOUNT + cBYID + "/{amountID}")
//	@ResponseBody
//	public Amount getAmountByID(@PathVariable(value = "amountID")
//	Long amountID)
//	{
//		return recipeService.getAmountByID(amountID);
//	}
}