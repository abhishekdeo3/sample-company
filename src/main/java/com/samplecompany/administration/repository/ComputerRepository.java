package com.samplecompany.administration.repository;

import com.samplecompany.administration.model.ComputerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {
}
