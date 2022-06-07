package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jackrkern.groceriesbyrecipe.business.AisleService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;

/* @author "Jack Kern" */

@Controller
public class AisleController
{
	private final AisleService aisleService;

	@Autowired
	public AisleController(AisleService aisleService)
	{
		this.aisleService = aisleService;
	}

	@RequestMapping(value = "/aisles", method = RequestMethod.GET)
	@ResponseBody
	public List<Aisle> getAisles(Model model)
	{
		List<Aisle> aisles = aisleService.getAisles();
		model.addAttribute("aisles", aisles);
		return aisles;
	}
}