package com.greenbone.samplecompany.repository;

import com.greenbone.samplecompany.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
