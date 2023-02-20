package com.vladofon.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladofon.scheduler.domain.Employee;
import com.vladofon.scheduler.domain.Task;
import com.vladofon.scheduler.service.SchedulerService;

@RestController
@RequestMapping(value = "/")
public class MainController {
	
	private final SchedulerService schedulerService;
	
	public MainController(@Autowired SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}
	
	@GetMapping
	public List<Employee> mainPage() {
		Employee e1 = new Employee();
		e1.setWeekHours(10L);
		e1.setId(1L);
		Employee e2 = new Employee();
		e2.setWeekHours(10L);e2.setId(2L);
		Employee e3 = new Employee();
		e3.setWeekHours(10L);e3.setId(3L);
		List<Employee> ee = List.of(e1,e2,e3);
		
		Task t1 = new Task();
		t1.setEstimatedTime(5L);
		Task t2 = new Task();
		t2.setEstimatedTime(5L);
		Task t3 = new Task();
		t3.setEstimatedTime(5L);
		Task t4 = new Task();
		t4.setEstimatedTime(5L);
		Task t5 = new Task();
		t5.setEstimatedTime(5L);
		Task t6 = new Task();
		t6.setEstimatedTime(5L);
		List<Task> tt = List.of(t1,t2,t3, t4, t5, t6);
		
		return schedulerService.generateSchedule(ee, tt, 1);
	}
}
