package com.vladofon.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladofon.scheduler.domain.Employee;
import com.vladofon.scheduler.domain.Task;
import com.vladofon.scheduler.repo.EmployeeRepo;
import com.vladofon.scheduler.repo.TaskRepo;

@Service
public class SchedulerService {
	
	private final EmployeeRepo employeeRepo;
	private final TaskRepo taskRepo;
	
	public SchedulerService(
		@Autowired EmployeeRepo employeeRepo,
		@Autowired TaskRepo taskRepo
	) {
		this.employeeRepo = employeeRepo;
		this.taskRepo = taskRepo;
	}
	
	public List<Employee> generateSchedule(List<Employee> employees, List<Task> tasks, int periodInWeeks) {
		int countOfEmployees = employees.size();
		int countOftasks = tasks.size();
		
		List<Employee> assigned = new ArrayList<>();
		
		for(int task = 0; task < countOftasks; task++) {
			for(int employee = 0; employee < countOfEmployees; employee++) {
				
				Task currentTask = tasks.get(task);
				Employee currentEmployee = employees.get(employee);
				
				Long employeeTotalHours = periodInWeeks * currentEmployee.getWeekHours();
				Long employeeLoadedHours = currentEmployee.getLoadedHours() + currentTask.getEstimatedTime();
				
				if(employeeTotalHours >= employeeLoadedHours) {
					currentEmployee.getTasks().add(currentTask);
					assigned.add(currentEmployee);
				}
				
			}
		}
		
		return assigned;
	}
}
