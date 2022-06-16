package com.jackrkern.groceriesbyrecipe.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String processRegistration(HttpServletRequest request, User user, Model model)
	{
		String decodedPassword = user.getPassword();
		userService.registerUser(user);
		try
		{
			if (user.getEmail() != null)
				request.login(user.getEmail(), decodedPassword);
		} catch (ServletException e)
		{
			e.printStackTrace();
			return "login";
		}
		return "items";
	}
}