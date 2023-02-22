package com.vladofon.scheduler.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladofon.scheduler.domain.Employee;
import com.vladofon.scheduler.repo.EmployeeRepo;

@Service
public class EmployeeService {
	private final EmployeeRepo employeeRepo;
	
	public EmployeeService(@Autowired EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}
	
	public Employee getOne(Long employeeId) throws Exception {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		
		if(employee.isEmpty()) {
			throw new Exception("Employee with id [" + employeeId + "] not found");
		}
		
		return employee.get();
	}
	
	public Employee create(Employee user) {
		return employeeRepo.save(user);
	}
	
	public Employee update(Employee user) {
		return employeeRepo.save(user);
	}
	
	public void delete(Long id) {
		employeeRepo.deleteById(id);
	}
}
