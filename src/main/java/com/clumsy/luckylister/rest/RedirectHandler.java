package com.clumsy.luckylister.rest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RedirectHandler {

	@RequestMapping("/login/facebook")
	public ModelAndView  login(ModelMap model) {
	    return new ModelAndView("/", model);
    }
	
	@RequestMapping("/shiny/login/facebook")
	public ModelAndView  shinyLogin(ModelMap model) {
	    return new ModelAndView("/shiny", model);
    }

	@RequestMapping("/shadow/login/facebook")
	public ModelAndView  shadowLogin(ModelMap model) {
	    return new ModelAndView("/shadow", model);
    }
	
	@RequestMapping("/hundo/login/facebook")
	public ModelAndView  hundoLogin(ModelMap model) {
	    return new ModelAndView("/hundo", model);
    }

	@RequestMapping("/shiny")
	public ModelAndView  shinyPage(ModelMap model) {
	    return new ModelAndView("/shiny_index.html", model);
    }
	
	@RequestMapping("/shadow")
	public ModelAndView  shadowPage(ModelMap model) {
	    return new ModelAndView("/shadow_index.html", model);
    }
	
	@RequestMapping("/hundo")
	public ModelAndView  hundoPage(ModelMap model) {
	    return new ModelAndView("/hundo_index.html", model);
    }

}
