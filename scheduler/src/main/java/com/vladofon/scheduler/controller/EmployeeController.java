package com.vladofon.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladofon.scheduler.domain.Employee;
import com.vladofon.scheduler.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(@Autowired EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping()
	public List<Employee> list() {
		return employeeService.getAll();
	}
	
	@GetMapping("{userId}")
	public Employee one(@PathVariable Long userId) throws Exception {
		return employeeService.getOne(userId);
	}
	
	@PostMapping()
	public String create(@RequestBody Employee user) {
		employeeService.create(user);
		return "User was created";
	}
	
	@PutMapping("{userId}")
	public String edit(@RequestBody Employee employee, @PathVariable Long userId) {
		employeeService.update(employee);
		return "User [" + userId + "] was edited";
	}
	
	@DeleteMapping("{userId}")
	public String delete(@PathVariable Long employeeId) {
		employeeService.delete(employeeId);
		
		return "Employee [" + employeeId + "] was deleted";
	}
}
