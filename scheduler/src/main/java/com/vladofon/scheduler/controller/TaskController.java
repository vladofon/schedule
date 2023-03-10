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

import com.vladofon.scheduler.domain.Task;
import com.vladofon.scheduler.dto.TaskFormDto;
import com.vladofon.scheduler.dto.TaskPreviewDto;
import com.vladofon.scheduler.service.TaskService;

@RestController
@RequestMapping(value = "task")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(@Autowired TaskService taskSevice) {
		this.taskService = taskSevice;
	}
	
	@GetMapping()
	public List<TaskPreviewDto> list() {
		return taskService.getAll();
	}
	
	@GetMapping("{taskId}")
	public Task one(@PathVariable Long taskId) {
		return taskService.getOne(taskId);
	}
	
	@PostMapping()
	public TaskPreviewDto create(@RequestBody TaskFormDto taskDto) throws Exception {
		return taskService.create(taskDto);
	}
	
	@PutMapping("{taskId}")
	public String edit(@RequestBody Task task, @PathVariable Long taskId) {
		taskService.update(task);
		return "Task [" + taskId + "] was edited";
	}
	
	@DeleteMapping("{taskId}")
	public String delete(@PathVariable Long taskId) {
		taskService.delete(taskId);
		
		return "Task [" + taskId + "] was deleted";
	}
	
	@GetMapping("/from-employee/{employeeId}")
	public List<TaskPreviewDto> employeeTasks(@PathVariable Long employeeId) throws Exception {
		return taskService.getByEmployee(employeeId);
	}
}
