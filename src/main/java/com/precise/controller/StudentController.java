package com.precise.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	
	private static Logger logger = Logger.getLogger(StudentController.class);
	
	@RequestMapping(value="/studentDashBoard")
	public String studentDashBoard(){
		logger.info("inside student dashboard method:::::::::::::::::::::::::");
		logger.info("inside student dashboard class:");
		return "StudentDashBoard";
	}

}
