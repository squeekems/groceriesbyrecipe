package com.jackrkern.groceriesbyrecipe.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.User;

/* @author "Jack Kern" */

@Controller
public class RegisterController
{

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String getRegister(Model model)
	{
		model.addAttribute("activePage", "register");
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public RedirectView processRegistration(HttpServletRequest request, User user)
	{
		String decodedPassword = user.getPassword();
		userService.registerUser(user);
		try
		{
			request.login(user.getEmail(), decodedPassword);
			return new RedirectView("/items");
		} catch (ServletException e)
		{
			e.printStackTrace();
			return new RedirectView("login");
		}
	}
}