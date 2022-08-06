package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
import static java.lang.System.out;

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
import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.time.format.DateTimeFormatter;

/* @author "Jack Kern" */

@Controller
public class RegisterController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private UserService userService;

	@GetMapping(REGISTER)
	public String getRegister(Model model)
	{
		model.addAttribute(ACTIVEPAGE, capitalize(strMapping(REGISTER)));
		model.addAttribute(strMapping(USER), new User());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(REGISTER));
		return strMapping(REGISTER);
	}

	@PostMapping(REGISTER)
	public RedirectView processRegistration(HttpServletRequest request, User user)
	{
		out.printf(	PERSONsTRIEDsTOsVERBnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(REGISTER));
		String decodedPassword = user.getPassword();
		userService.registerUser(user);
		try
		{
			request.login(user.getEmail(), decodedPassword);
			return new RedirectView(ITEMS);
		} catch (ServletException e)
		{
			e.printStackTrace();
			return new RedirectView(LOGIN);
		}
	}
}