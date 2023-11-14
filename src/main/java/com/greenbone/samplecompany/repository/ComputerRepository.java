package com.greenbone.samplecompany.repository;

import com.greenbone.samplecompany.model.ComputerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {
}
