package com.greenbone.samplecompany.service;

import com.greenbone.samplecompany.dto.Computer;
import com.greenbone.samplecompany.dto.Computers;
import com.greenbone.samplecompany.exception.NotFound;
import com.greenbone.samplecompany.model.ComputerEntity;
import com.greenbone.samplecompany.model.EmployeeEntity;
import com.greenbone.samplecompany.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Computers getComputersByEmployeeId(Long employeeId) {

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        if (employeeEntity.isEmpty()) {
            throw new NotFound(String.format("Employee with ID %s not found", employeeId));
        }

        Set<ComputerEntity> computerEntitySet = employeeEntity.get().getComputerEntitySet();

        if (!computerEntitySet.isEmpty()) {

            List<Computer> computerList = computerEntitySet.stream().map(computerEntity ->
                    new Computer(computerEntity.getComputerId(), computerEntity.getMacAddress(),
                            computerEntity.getComputerName(), computerEntity.getIpAddress(),
                            computerEntity.getEmployeeEntity().getAbbreviation(), computerEntity.getDescription())).toList();

            return new Computers(computerList);
        }

        return new Computers(new ArrayList<>());
    }
}