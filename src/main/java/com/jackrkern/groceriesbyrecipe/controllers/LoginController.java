package com.jackrkern.groceriesbyrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* @author "Jack Kern" */

@Controller
public class LoginController
{
	@GetMapping("/login")
	public String getLogin(Model model)
	{
		model.addAttribute("activePage", "login");
		return "login";
	}
}