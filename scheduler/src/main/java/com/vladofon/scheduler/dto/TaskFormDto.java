package com.vladofon.scheduler.dto;

import lombok.Data;

@Data
public class TaskFormDto {
	private Long id;
	private String title;
	private Long estimatedTime;
	private Long executor;
}
