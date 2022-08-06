/**
 * @package
 */
package com.jackrkern.groceriesbyrecipe.controllers;

import com.jackrkern.groceriesbyrecipe.business.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.jackrkern.groceriesbyrecipe.util.AppConstants.*;

/* @author "Jack Kern" */

@Controller
@RequestMapping({ FS, INDEX })
public class IndexController {

  Logger logger = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private UserService userService;

  @GetMapping
  public String getLogin(Model model) {
    model.addAttribute(ACTIVE_PAGE, capitalize(demap(LOGIN)));
    logger.info(space(new String[]{
        userService.getPrincipal().toString(),
        pastOf(demap(GET)),
        demap(INDEX)
    }) + PERIOD);
    return demap(INDEX);
  }
}
