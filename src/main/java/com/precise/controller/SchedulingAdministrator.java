package com.precise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SchedulingAdministrator {
	
	@RequestMapping(value="/schedulingAdministrator")
	public String openSchedulingAdministartorPage(){
		return "SchedulingAdministrator";
	}

}
