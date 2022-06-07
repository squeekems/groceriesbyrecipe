package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jackrkern.groceriesbyrecipe.business.ItemsService;
import com.jackrkern.groceriesbyrecipe.models.Item;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/items")
public class StoreItemsController
{
	private final ItemsService storeItemsService;

	@Autowired
	public StoreItemsController(ItemsService storeItemsService)
	{
		this.storeItemsService = storeItemsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getStoreItems(Model model)
	{
		List<Item> items = storeItemsService.getStoreItems();
		model.addAttribute("items", items);
		model.addAttribute("activePage", "items");
		return "items";
	}
}