package com.vladofon.scheduler.dto;

import com.vladofon.scheduler.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmployeePreviewDto {
	private Long id;
	private String name;
	private Long weekHours;
	private Long tasks;
	private Long loadedHours;
	
	public EmployeePreviewDto(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.weekHours = employee.getWeekHours();
		this.tasks = (long) employee.getTasks().size();
		this.loadedHours = employee.getLoadedHours();
	}
}
