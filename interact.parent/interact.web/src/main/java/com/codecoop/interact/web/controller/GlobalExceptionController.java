package com.codecoop.interact.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {
	private static final Logger LOG = Logger.getLogger(GlobalExceptionController.class);
	@ExceptionHandler(Exception.class)
	public ModelAndView handleCustomException(Exception ex) {
		ModelAndView model = new ModelAndView();
		model.setViewName("generic_error");
		LOG.error(ex.getMessage(), ex);
		return model;
	}
}
