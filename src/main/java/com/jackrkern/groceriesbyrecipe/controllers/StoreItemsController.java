package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jackrkern.groceriesbyrecipe.business.StoreItemsService;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/index")
public class StoreItemsController
{
	private final StoreItemsService storeItemsService;

	public StoreItemsController(StoreItemsService storeItemsService)
	{
		this.storeItemsService = storeItemsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getStoreItems(Model model)
	{
		model.addAttribute("guests", this.storeItemsService.getStoreItems());
		return "index";
	}
}