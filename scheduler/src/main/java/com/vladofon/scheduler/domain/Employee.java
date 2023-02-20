package com.vladofon.scheduler.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long weekHours;
	@OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
	private List<Task> tasks = new ArrayList<>();
	
	public Long getLoadedHours() {
		return Task.timeSum(tasks);
	}
}
