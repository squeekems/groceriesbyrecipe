package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
import static org.springframework.util.StringUtils.capitalize;
import static java.lang.System.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jackrkern.groceriesbyrecipe.business.UserService;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

import java.time.format.DateTimeFormatter;

/* @author "Jack Kern" */

@Controller
public class LoginController
{
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

	@Autowired
	private UserService userService;

	@GetMapping(LOGIN)
	public String getLogin(Model model)
	{
		model.addAttribute(ACTIVEPAGE, capitalize(strMapping(LOGIN)));
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : capitalize(SOMEONE),
					strMapping(LOGIN));
		return strMapping(LOGIN);
	}
}