package com.vladofon.scheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladofon.scheduler.domain.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{

}
