package com.vladofon.scheduler.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladofon.scheduler.domain.Employee;
import com.vladofon.scheduler.domain.Task;
import com.vladofon.scheduler.dto.TaskFormDto;
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
	
	public List<Task> getByEmployee(Long employeeId) throws Exception {
		Optional<Employee> executor = employeeRepo.findById(employeeId);
		
		if(executor.isPresent()) {
			return taskRepo.findByExecutor(executor.get());
		}
		
		throw new Exception("Employee with id [" + employeeId + "] was not found by getting emploees' tasks");
	}
	
	public Task create(TaskFormDto taskDto) throws Exception {

		return taskRepo.save(map(taskDto));
	}
	
	public Task create(Task task) {
		//task.setExecutor(employeeRepo.findById(1L).get());

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
	
	public Task map(TaskFormDto dto) throws Exception {
		Optional<Employee> executor = employeeRepo.findById(dto.getExecutor());
		
		if(executor.isPresent()) {
			Task task = new Task();
			task.setId(dto.getId());
			task.setEstimatedTime(dto.getEstimatedTime());
			task.setTitle(dto.getTitle());
			task.setExecutor(executor.get());
			
			return task;
		}
		
		throw new Exception("Executor with id [" + dto.getExecutor() + "] was not found in Task Form!");
	}
}
