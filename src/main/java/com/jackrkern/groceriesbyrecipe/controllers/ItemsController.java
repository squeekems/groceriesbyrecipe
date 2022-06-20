package com.jackrkern.groceriesbyrecipe.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Item;

/* @author "Jack Kern" */

@Controller
@RequestMapping("/items")
public class ItemsController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private RecipeService recipeService;

	@GetMapping
	public String getItems(HttpServletRequest request, String username, String password, Model model)
	{
		model.addAttribute("items", itemService.getItems(userService.getPrincipal()));
		model.addAttribute("item", new Item());
		model.addAttribute("activePage", "items");
		model.addAttribute("aisles", itemService.getAisles());
		model.addAttribute("unitsOfMeasurement", recipeService.getUnitsOfMeasurement());
		model.addAttribute("amounts", recipeService.getAmounts());
		return "items";
	}

	@RequestMapping("/getItemByID/{itemID}")
	@ResponseBody
	public Optional<Item> getItemByID(@PathVariable(value = "itemID")
	Long itemID)
	{
		return itemService.getItemByID(itemID);
	}

	// Add
	@PostMapping("/add")
	public RedirectView addItem(Item item, @RequestParam(value = "cmbAddAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		System.out.println(item.getDescription() + " Added");
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Added");
		return new RedirectView("/items");
	}

	// Edit
	@RequestMapping(value = "/edit", method = { RequestMethod.PUT, RequestMethod.GET })
	public RedirectView editItem(Item item, @RequestParam(value = "cmbEditAisle")
	Long aisleID, RedirectAttributes redirectAttributes)
	{
		item.setAisle(itemService.getAisleByID(aisleID));
		item.setUser(userService.getPrincipal());
		itemService.saveItem(item);
		System.out.println(item + " Edited");
		redirectAttributes.addFlashAttribute("success", item.getDescription() + " Edited");
		return new RedirectView("/items");
	}

	// Delete
	@GetMapping("/remove/{itemID}")
	public RedirectView deleteItem(@PathVariable(value = "itemID")
	Long itemID, RedirectAttributes redirectAttributes)
	{
		Item item = itemService.getItemByID(itemID).get();
		itemService.deleteItem(itemID);
		System.out.println(item.getDescription() + " Removed");
		redirectAttributes.addFlashAttribute(item.getDescription() + " Removed");
		return new RedirectView("/items");
	}

//	@GetMapping("/items/selected")
//	public String passItemID(Model model)
//	{
//		model.att
//		return "items";
//	}

//	@DeleteMapping("/items")
//	public String deleteItem(Long itemID, Model model)
//	{
//		itemService.deleteItem(itemID);
//		addItemAttributes(model);
//		return "items";
//	}
}