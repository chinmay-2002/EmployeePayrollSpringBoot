package net.chinmay.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import net.chinmay.model.Employee;
import net.chinmay.service.EmployeeService;

@Controller
public class HomeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	
	
	@GetMapping("/signin")
	public String showLoginForm() {
		System.out.println("Processing!!!!!!!!");
		return "login";
	}
	
	@PostMapping("/signin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, ModelAndView model, Model m) {
//		String loggedInEmail = (String) session.getAttribute("email");
//
//	    // Check if user is logged in
//	    if (loggedInEmail == null) {
//	        // Redirect to signin page if user is not logged in
//	        return "redirect:/signin";
//	    }
		System.out.println("Processing!!!!!!!!");
		if(email==null) {
			return "redirect:/";
		}
		boolean isAuthenticated = employeeService.authenticate(email, password);
        if (isAuthenticated) {
            session.setAttribute("email", email); // Store email in session
            Employee employee = employeeService.findByEmail(email);
            m.addAttribute("employee", employee);
            session.setAttribute("employee", employee); 
            System.out.println("Sapadle re bhava !!!" + employee.toString());
            model.addObject("employee", employee);
            return "/employee/home";
        } else {
            session.setAttribute("msg", "Invalid email or password");
            return "redirect:/signin";
        }
    }
	
	  @GetMapping("/home")
	    public String showDashboard(HttpSession session) {
		  System.out.println("Getmapping home 58");
	        if (session.getAttribute("email") != null) {
	            return "employee/";
	        } else {
	            return "redirect:/signin"; // Redirect to login page if user is not logged in
	        }
	    }
	  
	  
		 
//		 @GetMapping("/home")
//		    public String home(@AuthenticationPrincipal Employee employee,Model model) {
//			 String email = employee.getEmail(); // Assuming email is stored as username
//
//		        
//			        Employee emp = employeeService.findByEmail(email);
//
//			        // Pass user data to the view
//			        model.addAttribute("emp", emp);
//
//			        
//			        return "employee/";
//			 	
//		    }
		
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/homee")
	public String showHomee() {
		return "homee";
	}
	
	@PostMapping("/update")
	public ModelAndView updateEmployee(
	@RequestParam("name") String name,
	@RequestParam("email") String email,
	@RequestParam("salary") String salary,
	@RequestParam("designation") String
	designation, ModelAndView model) {
	// Retrieve the employee from the database
	Employee employee = employeeService.findByEmail(email);
	System.out.println("employee in update: " + employee);
	// Update employee details
	employee.setName(name);
	employee.setEmail(email);
	employee.setSalary(salary);
	employee.setDesignation(designation);
	// Save the updated employee
	employeeService.createEmployee(employee);
	model.addObject("success", "Employee details updated successfully");
	model.addObject("employee", employee);
	model.setViewName("redirect:/employee/home");
	return model;
	}

	
	
	
	
	
	
	@PostMapping("/createEmployee")
	public String createEmployee(@ModelAttribute Employee employee, HttpSession session) {
		
		boolean f = employeeService.checkEmail(employee.getEmail());
		if(f) {
			session.setAttribute("msg", "User Already Exist");
			return "redirect:/register";
		}
		
				
		
//		System.out.println(employee);
		employeeService.createEmployee(employee);
		session.setAttribute("msg", "Employee Registered Successfully");
		return "redirect:/register";
	}
	@GetMapping("/clearMessage")
	public String clearMessage(HttpSession session) {
	    session.removeAttribute("msg");
	    return "redirect:/register"; // Redirect to your register page
	}
	
	
//	
//	 @PostMapping("/update")
//	    public String update(@ModelAttribute("employee") Employee emp) {
//	        // Update user data in the database
//	        employeeService.update(emp);
//	        
//	        // Redirect back to the home page after updating
//	        return "redirect:/employee/";
//	    }
	 
	 
	 


	
}
