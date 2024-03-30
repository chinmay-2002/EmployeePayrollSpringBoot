package net.chinmay.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;
import net.chinmay.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController  {
	
	
	@GetMapping("/home")
	public String home() {
		return "employee/home";
	}	
	
	
	
}
