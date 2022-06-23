package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/settings")
public class SettingsController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public void getSettings(Model model)
	{
		model.addAttribute("aisles", itemService.getAisles());
		model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		model.addAttribute("amounts", recipeService.getAmounts());
	}
}