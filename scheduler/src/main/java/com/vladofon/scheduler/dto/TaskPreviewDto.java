package com.vladofon.scheduler.dto;

import org.springframework.stereotype.Component;

import com.vladofon.scheduler.domain.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskPreviewDto {
	private final Long id;
	private final String title;
	private final Long estimatedTime;
	private final Long executor;
	
	public TaskPreviewDto(Task task) {
		this.id = task.getId();
		this.title = task.getTitle();
		this.estimatedTime = task.getEstimatedTime();
		this.executor = task.getExecutor().getId();
	}
}
