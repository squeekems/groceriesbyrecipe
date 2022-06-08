package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemsService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Item;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/items")
public class StoreItemsController
{
	@Autowired
	private ItemsService storeItemsService;

	@GetMapping
	public String getStoreItems(Model model)
	{
		List<Aisle> aisles = storeItemsService.getAisles();
		model.addAttribute("aisles", aisles);

		List<Item> items = storeItemsService.getStoreItems();
		model.addAttribute("items", items);
		model.addAttribute("activePage", "items");
		return "items";
	}
}