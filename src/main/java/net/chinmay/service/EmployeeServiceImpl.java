package net.chinmay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chinmay.model.Employee;
import net.chinmay.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee createEmployee(Employee emp) {
		
		return employeeRepository.save(emp);
	}

	@Override
	public boolean checkEmail(String email) {
		
		return employeeRepository.existsByEmail(email);
	}

	@Override
	public boolean authenticate(String email, String password) {
		 // Retrieve employee by email from the database
        Employee employee = employeeRepository.findByEmail(email);

        // Check if employee exists and if the provided password matches
        if (employee != null && employee.getPassword().equals(password)) {
            return true; // Authentication successful
        } else {
            return false; // Authentication failed
        }
	}
	
	public void update(Employee emp) {
        employeeRepository.save(emp);
    }

	@Override
	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}
	
	

}
