package com.jackrkern.groceriesbyrecipe.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jackrkern.groceriesbyrecipe.business.ItemService;
import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.Aisle;
import com.jackrkern.groceriesbyrecipe.models.Item;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Controller
public class AppController
{
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public String getList(Model model)
	{
		model.addAttribute("activePage", "list");
		return "list";
	}

	@GetMapping("/recipes")
	public String getRecipes(Model model)
	{
		model.addAttribute("activePage", "recipes");
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
		authWithHttpServletRequest(request, user.getEmail(), decodedPassword, model);
		return "items";
	}

	@GetMapping("/items")
	public String authWithHttpServletRequest(HttpServletRequest request, String username, String password, Model model)
	{
		try
		{
			if (username != null)
				request.login(username, password);
			List<Aisle> aisles = itemService.getAisles();
			model.addAttribute("aisles", aisles);

			List<Item> items = itemService.getItems();
			model.addAttribute("items", items);
			model.addAttribute("activePage", "items");
			return "items";
		} catch (ServletException e)
		{
			e.printStackTrace();
			return "login";
		}
	}

	public String getitems(Model model)
	{
		List<Aisle> aisles = itemService.getAisles();
		model.addAttribute("aisles", aisles);

		List<Item> items = itemService.getItems();
		model.addAttribute("items", items);
		model.addAttribute("activePage", "items");
		return "items";
	}
}