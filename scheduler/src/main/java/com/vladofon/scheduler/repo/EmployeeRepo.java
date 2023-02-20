package com.vladofon.scheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vladofon.scheduler.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
