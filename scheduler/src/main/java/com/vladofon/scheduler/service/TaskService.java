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
public class TaskService {
	
	private final EmployeeRepo employeeRepo;
	private final TaskRepo taskRepo;
	
	public TaskService(@Autowired EmployeeRepo employeeRepo, @Autowired TaskRepo taskRepo) {
		this.employeeRepo = employeeRepo;
		this.taskRepo = taskRepo;
	}
	
	public List<Task> getAll() {
		return taskRepo.findAll();
	}
	
	public Task getOne(Long id) {
		return taskRepo.findById(id).get();
	}
	
	public Task create(Task task) {
		task.setExecutor(employeeRepo.findById(1L).get());

		return taskRepo.save(task);
	}
	
	public Task update(Task task) {
		Task taskFromDb = taskRepo.findById(task.getId()).get();
		
		task.setExecutor(taskFromDb.getExecutor());
		
		return taskRepo.save(task);
	}
	
	public void delete(Long id) {
		taskRepo.deleteById(id);
	}
	
	
	public List<Task> generateUserTasks(Employee employee) {
		List<Task> tasks = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			Task task = new Task();
			task.setExecutor(employee);
			
			tasks.add(task);
		}
		
		return taskRepo.saveAll(tasks);
	}
}
