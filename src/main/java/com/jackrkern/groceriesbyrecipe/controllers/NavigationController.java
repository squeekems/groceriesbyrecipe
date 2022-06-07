package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* @author "Jack Kern" */

@Controller
public class NavigationController
{
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
		return "register";
	}
}