package com.jackrkern.groceriesbyrecipe.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.RecipeService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Amount;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.Recipe;
import com.jackrkern.groceriesbyrecipe.models.UnitOfMeasurement;
import com.jackrkern.groceriesbyrecipe.models.User;
import com.jackrkern.groceriesbyrecipe.util.FormDTO;

/* @author "Jack Kern" */

@Controller
public class AppController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private RecipeService recipeService;

	@GetMapping("/list")
	public String getList(Model model)
	{
		model.addAttribute("activePage", "list");
		addSettingsAttributes(model);
		return "list";
	}

	@GetMapping("/recipes")
	public String getRecipes(Model model)
	{
		model.addAttribute("activePage", "recipes");
		List<Recipe> recipes = recipeService.getRecipes();
		model.addAttribute("recipes", recipes);
		addSettingsAttributes(model);
		return "recipes";
	}

	@GetMapping("/login")
	public String getLogin(Model model)
	{
		model.addAttribute("activePage", "login");
		return "login";
	}

	@GetMapping("/register")
	public String getRegister(Model model)
	{
		model.addAttribute("activePage", "register");
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String processRegistration(HttpServletRequest request, User user, Model model)
	{
		String decodedPassword = user.getPassword();
		userService.registerUser(user);
		getItems(request, user.getEmail(), decodedPassword, model);
		return "items";
	}

//	@GetMapping("/items/selected")
//	public String passItemID(Model model)
//	{
//		model.att
//		return "items";
//	}

	@GetMapping("/items")
	public String getItems(HttpServletRequest request, String username, String password, Model model)
	{
		try
		{
			if (username != null)
				request.login(username, password);
			addItemAttributes(model);
			addSettingsAttributes(model);
			return "items";
		} catch (ServletException e)
		{
			e.printStackTrace();
			return "login";
		}
	}

	@PostMapping("/items")
	public String addItem(Principal principal, FormDTO formDTO, Model model)
	{
		Item item = new Item(	formDTO.getField1(), itemService.getAisleByName(formDTO.getField2()),
								userService.getByEmail(principal.getName()));
		itemService.addItem(item);
		addItemAttributes(model);
		return "items";
	}

//	@DeleteMapping("/items")
//	public String deleteItem(Long itemID, Model model)
//	{
//		itemService.deleteItem(itemID);
//		addItemAttributes(model);
//		return "items";
//	}

	@GetMapping("/items/{itemID}")
	public String deleteItem(@PathVariable(value = "itemID")
	Long itemID)
	{
		itemService.deleteItem(itemID);
		System.out.println("This is delete");
		return "redirect:/items";
	}

	private void addItemAttributes(Model model)
	{

		List<Item> items = itemService.getItems(userService.getPrincipal());
		model.addAttribute("items", items);
		model.addAttribute("activePage", "items");
		model.addAttribute("formDTO", new FormDTO());
	}

	private void addSettingsAttributes(Model model)
	{
		List<Aisle> aisles = itemService.getAisles();
		model.addAttribute("aisles", aisles);
		List<UnitOfMeasurement> unitsOfMeasurement = recipeService.getUnitsOfMeasurement();
		model.addAttribute("unitsOfMeasurement", unitsOfMeasurement);
		List<Amount> amounts = recipeService.getAmounts();
		model.addAttribute("amounts", amounts);
	}
}