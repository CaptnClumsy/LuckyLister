package com.clumsy.luckylister.rest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ShinyHandler {

	@RequestMapping("/shiny")
	public ModelAndView  login(ModelMap model) {
	    return new ModelAndView("/shiny_index.html", model);
    }

}
