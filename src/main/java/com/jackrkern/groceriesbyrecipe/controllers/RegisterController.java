/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.controllers;

import com.jackrkern.groceriesbyrecipe.business.UserService;
import com.jackrkern.groceriesbyrecipe.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Controller
@RequestMapping(REGISTER)
public class RegisterController {

	Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public String getRegister(Model model) {
		model.addAttribute(ACTIVE_PAGE, capitalize(demap(REGISTER)));
		model.addAttribute(demap(USER), new User());
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(demap(GET)),
				demap(REGISTER)
		}) + PERIOD);
		return demap(REGISTER);
	}

	@PostMapping
	public RedirectView processRegistration(
			RedirectAttributes redirectAttributes,
			HttpServletRequest request,
			User user,
			BindingResult bindingResult,
			@RequestParam(value = TXT_CONFIRM_PASSWORD) String decodedPassword) {
		logger.info(space(new String[]{
				userService.getPrincipal().toString(),
				pastOf(TRY),
				TO,
				demap(REGISTER)
		}) + PERIOD);
		if (bindingResult.hasErrors()) {
			return new RedirectView(REGISTER);
		}
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			bindingResult.rejectValue(
					demap(EMAIL),
					String.format(S_IN_USE, demap(EMAIL)),
					String.format(S_IS_ALREADY_IN_USE, capitalize(demap(EMAIL)))
			);
			redirectAttributes.addFlashAttribute(
					DANGER, String.format(S_IS_ALREADY_IN_USE, capitalize(demap(EMAIL)))
			);
			return new RedirectView(REGISTER);
		}
		try {
			request.login(user.getEmail(), decodedPassword);
			redirectAttributes.addFlashAttribute(
					SUCCESS, String.format(WELCOME_S, userService.getByEmail(user.getEmail()))
			);
			return new RedirectView(ITEMS);
		} catch (ServletException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(DANGER, INVALID_CREDENTIALS);
			return new RedirectView(LOGIN);
		}
	}
}