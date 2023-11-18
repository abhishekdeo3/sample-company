package com.samplecompany.administration.service;

import com.samplecompany.administration.dto.Computer;
import com.samplecompany.administration.dto.Computers;
import com.samplecompany.administration.dto.Employee;
import com.samplecompany.administration.exception.NotFoundException;
import com.samplecompany.administration.model.ComputerEntity;
import com.samplecompany.administration.model.EmployeeEntity;
import com.samplecompany.administration.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Employee getComputersByEmployeeId(Long employeeId) {

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        if (employeeEntity.isEmpty()) {
            throw new NotFoundException(String.format("Employee with ID %s not found", employeeId));
        }

        Set<ComputerEntity> computerEntitySet = employeeEntity.get().getComputerEntitySet();

        List<Computer> computerList = new ArrayList<>();

        if (!computerEntitySet.isEmpty()) {

            computerList = computerEntitySet.stream().map(computerEntity ->
                    new Computer(computerEntity.getComputerId(), computerEntity.getMacAddress(),
                            computerEntity.getComputerName(), computerEntity.getIpAddress(),
                            computerEntity.getEmployeeEntity().getAbbreviation(), computerEntity.getDescription())).toList();
        }

        return new Employee(employeeEntity.get().getFirstName(), employeeEntity.get().getLastName(),
                employeeEntity.get().getAbbreviation(), new Computers(computerList));
    }
}
