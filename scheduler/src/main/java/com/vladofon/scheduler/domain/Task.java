package com.vladofon.scheduler.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long estimatedTime;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee executor;
	
	public static Long timeSum(List<Task> tasks) {
		return (tasks != null && tasks.size() != 0) ? tasks.stream().mapToLong(task -> task.getEstimatedTime()).sum() : 0;
	}
}
