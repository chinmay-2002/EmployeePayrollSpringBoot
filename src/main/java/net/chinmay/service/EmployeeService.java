package net.chinmay.service;

import net.chinmay.model.Employee;


public interface EmployeeService {
	public Employee createEmployee(Employee emp);
	public boolean checkEmail(String email);
	public boolean authenticate(String email, String password);
	public void update(Employee emp);
	public Employee findByEmail(String email);

}
