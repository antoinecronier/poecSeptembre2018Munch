package com.medievalgrosbill.error;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.medievalgrosbill.security.controllers.LoginController;

@ControllerAdvice
public class DefaultErrorsController {

	@ExceptionHandler(value= {Exception.class})
	public ModelAndView handleAllExceptions(Exception ex, WebRequest request, HttpServletRequest http) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (ex instanceof AccessDeniedException || ex instanceof Unauthorized) {
			modelAndView.addObject("form_username", LoginController.FORM_USERNAME);
			modelAndView.addObject("form_password", LoginController.FORM_PASSWORD);
			modelAndView.setViewName("/security/login");
		} else {
			ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
					request.getDescription(false));

			modelAndView.addObject("error", exceptionResponse);

			modelAndView.setViewName("/errors/" + HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return modelAndView;
	}
}

