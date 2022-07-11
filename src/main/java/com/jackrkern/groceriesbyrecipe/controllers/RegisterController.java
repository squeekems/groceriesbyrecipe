package com.jackrkern.groceriesbyrecipe.controllers;

import static java.time.LocalDateTime.now;
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

	@GetMapping(sREGISTER)
	public String getRegister(Model model)
	{
		model.addAttribute(ACTIVEPAGE, cREGISTER);
		model.addAttribute(USER, new User());
		out.printf(	PERSONsLOADEDsTHEsNOUNsPAGEnl, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, REGISTER);
		return REGISTER;
	}

	@PostMapping(sREGISTER)
	public RedirectView processRegistration(HttpServletRequest request, User user)
	{
		out.printf(	PERSONsTRIEDsTOsVERBp, now().format(dateTimeFormatter),
					userService.getPrincipal() != null ? userService.getPrincipal() : cSOMEONE, REGISTER);
		String decodedPassword = user.getPassword();
		userService.registerUser(user);
		try
		{
			request.login(user.getEmail(), decodedPassword);
			return new RedirectView(sITEMS);
		} catch (ServletException e)
		{
			e.printStackTrace();
			return new RedirectView(sLOGIN);
		}
	}
}